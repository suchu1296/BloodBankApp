package com.example.riya.bloodbank;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    Spinner bloodgroup, spinner1, spinner;
    String search_url = "http://192.168.43.231/f.php";
    String search_url1 = "http://192.168.43.231/finddonor1.php";
    String result;
    String[] name;
    Button sendBtn;
    String result1 = "";
    String data;
    AlertDialog alertDialog;
    EditText e5;
    String b5,c5;
    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<String>();
    ArrayList<String> listitem = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood_group, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Button register = (Button) findViewById(R.id.button2);
        bloodgroup = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner2);

        bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                try {
                    String bloodgroup = spinner.getSelectedItem().toString();

                    URL url = new URL(search_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                    String post_data = URLEncoder.encode(" bloodgroup", "utf-8") + "=" + URLEncoder.encode(bloodgroup, "utf-8");


                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    result = "";

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;

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
                    JSONArray JA = new JSONArray(result);
                    list1.clear();
                    for (int i = 0; i < JA.length(); i++) {

                        JSONObject json = JA.getJSONObject(i);
                        list1.add(json.getString("city"));
                    }
                    listitem.clear();
                    listitem.addAll(list1);
                    adapter.notifyDataSetChanged();
                    spinner_fn();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    protected void sendSMSMessage() {
        String phoneNo = e5.getText().toString();
        String message = list2.toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
            e5.setText("");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void onSearch(View view) {


        String bloodgroup = spinner.getSelectedItem().toString();
        String city = spinner1.getSelectedItem().toString();
        b5=bloodgroup.toString();
        c5=city.toString();
        Intent i2 = new Intent(MainActivity.this, BloodDonorDetails.class);
        i2.putExtra("bloodgroup5",b5);
        i2.putExtra("city5",c5);
        startActivity(i2);
        String line = "";
    }

    public void register(View v) {
        Intent i = new Intent(MainActivity.this, register_activity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void showLogin(MenuItem item)
    {
        Intent i=new Intent(MainActivity.this,login_activity.class);
        startActivity(i);
    }
    public void showInstr(MenuItem item)
    {
        Intent i=new Intent(MainActivity.this,instructions.class);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_login:
                showLogin(item);
                return true;
            case R.id.action_login1:
                showInstr(item);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void spinner_fn()
    {
        ArrayAdapter<String> dataadApter1 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,listitem);
        spinner1.setAdapter(dataadApter1);

    }
}
