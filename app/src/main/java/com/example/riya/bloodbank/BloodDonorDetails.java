package com.example.riya.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BloodDonorDetails extends AppCompatActivity {
    String s, s1;
    String search_url1 = "http://192.168.43.231/finddonor1.php";
    String result1;
    List<cources> collegeList1;
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<cources> list3 = new ArrayList<cources>();
    String data;
    ArrayList<String> data1=new ArrayList<String>();
    cources c1;
    Button b1,sendBtn;
    EditText et9;
    ArrayList<HashMap<String, String>> personList;
    private static final String TAG_name = "name";
    private static final String TAG_bloodgroup = "bloodgroup";
    private static final String TAG_contactno = "contactno";
    private static final String TAG_gender = "gender";
    private static final String TAG_ADD = "address";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donor_details);
        b1 = (Button) findViewById(R.id.back);
        personList = new ArrayList<HashMap<String, String>>();
        this.context = context;

        Intent i2 = getIntent();
        s = i2.getStringExtra("bloodgroup5");
        s1 = i2.getStringExtra("city5");

        try {
            URL url = new URL(search_url1);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            String post_data = URLEncoder.encode(" bloodgroup", "utf-8") + "=" + URLEncoder.encode(s, "utf-8") + "&"
                    + URLEncoder.encode("city", "utf-8") + "=" + URLEncoder.encode(s1, "utf-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            result1 = "";

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result1 += line;

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONArray JA = new JSONArray(result1);
            list2.clear();
            data = "";
            for (int i = 0; i < JA.length(); i++) {

                JSONObject json = JA.getJSONObject(i);
                json = JA.getJSONObject(i);

                list2.add(json.getString("name"));
                list2.add(json.getString("bloodgroup"));
                list2.add(json.getString("contactno"));
                list2.add(json.getString("address"));
                list2.add(json.getString("city"));
                list2.add(json.getString("gender"));
                list2.add(json.getString("age"));
                  data1.add("Name="+ json.getString("name")+
                         "\n"+"Bloodgroup="+ json.getString("bloodgroup")+
                         "\n"+"ContactNo="+ json.getString("contactno")+
                         "\n"+"Address="+ json.getString("address")+
                         "\n"+"City="+ json.getString("city")+
                         "\n"+"Gender="+ json.getString("gender")+
                         "\n"+"Age="+ json.getString("age")+
                          "\n"+"___________________________");


                String id1 = json.getString(TAG_name);
                String id2 = json.getString(TAG_bloodgroup);
                String id3 = json.getString(TAG_contactno);
                String id4 = json.getString(TAG_gender);
                String id5 = json.getString(TAG_ADD);
                HashMap<String, String> persons = new HashMap<String, String>();

                persons.put(TAG_name, id1);
                persons.put(TAG_bloodgroup, id2);
                persons.put(TAG_contactno, id3);
                persons.put(TAG_gender, id4);
                persons.put(TAG_ADD, id5);

                personList.add(persons);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView3 = (ListView) findViewById(R.id.listView3);

        ListAdapter listAdapter = new ArrayAdapter<String>(BloodDonorDetails.this, R.layout.layout,
                list2);
        ListAdapter adapter = new SimpleAdapter(
                BloodDonorDetails.this, personList, R.layout.list_item,
                new String[]{TAG_name, TAG_bloodgroup, TAG_contactno, TAG_gender, TAG_ADD},
                new int[]{R.id.name, R.id.bloodgroup, R.id.contactno, R.id.gender, R.id.address}
        );

        listView3.setAdapter(adapter);
    }

    public void OnBack(View v1) {
        Intent i3 = new Intent(BloodDonorDetails.this, MainActivity.class);
        startActivity(i3);
    }

    public void OnShare(View v1) {
        Intent si = new Intent();
        si.setAction(Intent.ACTION_SEND);
        si.setType("text/plain");
        si.putExtra(Intent.EXTRA_TEXT, data1.toString());
        try {
            startActivity(Intent.createChooser(si, "Send Information..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(BloodDonorDetails.this, "There is no information client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
