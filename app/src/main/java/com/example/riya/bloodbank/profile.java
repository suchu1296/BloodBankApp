package com.example.riya.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class profile extends AppCompatActivity {
    private  static final int RESULT_LOAD_IMAGE=1;
    private  static final int RESULT_LOAD_IMAGE1=1;
    ImageView imagetoupload;
    String edit1_url="http://192.168.43.231/edit2.php";
    EditText name,  contactno, address, city,  age, username, password;
    EditText name1,  contactno1, address1, city1,  age1,  password1;
    String name2,  contactno2, address2, city2,  age2,  password2,username1;
    Spinner bloodgroup,gender;
    String x2,availability;
    String str_availability;
    String s="";
    String s1="";
    String s2="";
    String s3="";
    String s4="";
    String s5="";
    String s6="";
    String s7="";
    String s8="";
    ArrayList<String> listitem=new ArrayList<>();
    ArrayList<String> listitem2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        imagetoupload=(ImageView)findViewById(R.id.imageView);
        name=(EditText)findViewById(R.id.enter_name);
        bloodgroup=(Spinner)findViewById(R.id.spinner);
        contactno=(EditText)findViewById(R.id.editText2);
        address=(EditText)findViewById(R.id.editText3);
        city=(EditText)findViewById(R.id.editText4);
        gender=(Spinner)findViewById(R.id.spinner1);
        age=(EditText)findViewById(R.id.editText8);

        password=(EditText)findViewById(R.id.editText7);
        Intent i=getIntent();
        s=i.getStringExtra("name");
        s1=i.getStringExtra("bloodgroup");
        s2=i.getStringExtra("contactno");
        s3=i.getStringExtra("sddress");
        s4=i.getStringExtra("city");
        s5=i.getStringExtra("gender");
        s6=i.getStringExtra("age");
        s7=i.getStringExtra("password");
        s8=i.getStringExtra("username");
        listitem.add(s1);
        listitem2.add(s5);

        name.setText(s);
        contactno.setText(s2);
        address.setText(s3);
        city.setText(s4);
        age.setText(s6);
        password.setText(s7);
        ArrayAdapter<String> dataadApter1 = new ArrayAdapter<String>(profile.this,android.R.layout.simple_spinner_item,listitem);
        bloodgroup.setAdapter(dataadApter1);
        ArrayAdapter<String> dataadApter2 = new ArrayAdapter<String>(profile.this,android.R.layout.simple_spinner_item,listitem2);
        gender.setAdapter(dataadApter2);

        imagetoupload=(ImageView)findViewById(R.id.imageView);
        name1=(EditText)findViewById(R.id.enter_name);
        bloodgroup=(Spinner)findViewById(R.id.spinner);
        contactno1=(EditText)findViewById(R.id.editText2);
        address1=(EditText)findViewById(R.id.editText3);
        city1=(EditText)findViewById(R.id.editText4);
        gender=(Spinner)findViewById(R.id.spinner1);
        age1=(EditText)findViewById(R.id.editText8);
        password1=(EditText)findViewById(R.id.editText7);

    }

    public void uploadImage(View v)
    {
        Intent j = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(j, RESULT_LOAD_IMAGE);
    }
    public  void uploadPdf(View view)
    {
        Intent i1=new Intent(Intent.ACTION_PICK);
        Intent i2=new Intent("android.media.action.FILE_CAPTURE");
        i1.putExtra(Intent.EXTRA_INTENT,i2);

        startActivityForResult(i1,RESULT_LOAD_IMAGE1);
    }
    public void itemClicked1(View v) {

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.c2);
        if(checkBox1.isChecked()){
            x2="yes";

        }
        else{x2="no";}
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && requestCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            imagetoupload.setImageURI(selectedimage);

        }
        if (requestCode == RESULT_LOAD_IMAGE1 && requestCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onEdit(View view)
    {

        if(name.getText().toString().length() == 0 || contactno.getText().toString().length() == 0 || address.getText().toString().length() == 0 || city.getText().toString().length() == 0 || age.getText().toString().length() == 0 ||  password.getText().toString().length() == 0 || password.getText().toString().length()<=8|| password.getText().toString().length()>=16) {


            if(name.getText().toString().length() == 0 ) {
                name.setError("requires");
            }

            if(contactno.getText().toString().length() == 0 ) {
                contactno.setError("requires");
            }

            if(address.getText().toString().length() == 0 ) {
                address.setError("requires");
            }

            if(city.getText().toString().length() == 0 ) {
                city.setError("requires");
            }

            if(age.getText().toString().length() == 0 ) {
                age.setError("requires");
            }



            if(password.getText().toString().length() == 0 || password.getText().toString().length()<=8|| password.getText().toString().length()>=16 ) {
                password.setError("requires or length must be between 8 to 16 ");
            }

        }
        else {

            try
            {
                imagetoupload = (ImageView) findViewById(R.id.imageView);
                name1 = (EditText) findViewById(R.id.enter_name);
                bloodgroup = (Spinner) findViewById(R.id.spinner);
                contactno1 = (EditText) findViewById(R.id.editText2);
                address1 = (EditText) findViewById(R.id.editText3);
                city1 = (EditText) findViewById(R.id.editText4);
                gender = (Spinner) findViewById(R.id.spinner1);
                age1 = (EditText) findViewById(R.id.editText8);
                password1 = (EditText) findViewById(R.id.editText7);

                name2 = name1.getText().toString();
                contactno2 = contactno1.getText().toString();
                address2 = address1.getText().toString();
                city2 = city1.getText().toString();
                age2 = age1.getText().toString();
                username1 = s8.toString();
                password2 = password1.getText().toString();
                if(x2.equals("yes"))
                {
                    availability="yes";
                }
                else if(x2.equals("no"))
                {
                    availability="no";
                }
                str_availability = availability;

                Toast.makeText(getApplicationContext(),name2,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),str_availability,Toast.LENGTH_LONG).show();
                URL url = new URL(edit1_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String post_data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode(name2, "utf-8") + "&"
                        + URLEncoder.encode("contactno", "utf-8") + "=" + URLEncoder.encode(contactno2, "utf-8") + "&"
                        + URLEncoder.encode("address", "utf-8") + "=" + URLEncoder.encode(address2, "utf-8") + "&"
                        + URLEncoder.encode("city", "utf-8") + "=" + URLEncoder.encode(city2, "utf-8") + "&"
                        + URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode(age2, "utf-8") + "&"
                        + URLEncoder.encode("username", "utf-8") + "=" + URLEncoder.encode(username1, "utf-8") + "&"
                        + URLEncoder.encode("password", "utf-8") + "=" + URLEncoder.encode(password2, "utf-8")+ "&"
                        + URLEncoder.encode("str_availability", "utf-8") + "=" + URLEncoder.encode(str_availability, "utf-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
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
            Toast.makeText(getApplicationContext(),"Edit Successgull",Toast.LENGTH_LONG).show();
        }
        }
}
