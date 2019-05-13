package com.example.riya.bloodbank;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

import android.widget.Button;



public class login_activity extends Activity {
    private EditText editTextUserName;
    private EditText editTextPassword;

    public static final String USER_NAME = "USERNAME";

    String username;
    String password1;
    String result;
    String s="";
    String s1="";
    String s2="";
    String s3="";
    String s4="";
    String s5="";
    String s6="";
    String s7="";
    String s8="";

    String login_url = "http://192.168.43.231/login2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        Button register = (Button) findViewById(R.id.button2);
        Button finddonor1 = (Button) findViewById(R.id.button3);

        editTextUserName = (EditText) findViewById(R.id.etusername);
        editTextPassword = (EditText) findViewById(R.id.etpassword);
    }


    public void register(View v) {
        Intent i = new Intent(login_activity.this, register_activity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void invokeLogin(View view) {
        username = editTextUserName.getText().toString();
        password1 = editTextPassword.getText().toString();
        String type = "login";

        try {
            String user_name = username;
            String password = password1;
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            String post_data = URLEncoder.encode("user_name", "utf-8") + "=" + URLEncoder.encode(user_name, "utf-8") + "&"
                    + URLEncoder.encode("password", "utf-8") + "=" + URLEncoder.encode(password, "utf-8");


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
            for (int i = 0; i < JA.length(); i++) {
                JSONObject json = JA.getJSONObject(i);
                json = JA.getJSONObject(i);

                 s=json.getString("username");
                s1=json.getString("name");
                s2=json.getString("bloodgroup");
                s3=json.getString("contactno");
                s4=json.getString("address");
                s5=json.getString("city");
                s6=json.getString("gender");
                s7=json.getString("age");
                s8=json.getString("password");

//                 Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            if (s.equals(username))
            {
                Intent i = new Intent(login_activity.this, profile.class);
                i.putExtra("name",s1);
                i.putExtra("bloodgroup",s2);
                i.putExtra("contactno",s3);
                i.putExtra("sddress",s4);
                i.putExtra("city",s5);
                i.putExtra("gender",s6);
                i.putExtra("age",s7);
                i.putExtra("password",s8);
                i.putExtra("username",s);
                startActivity(i);

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Login Not Success", Toast.LENGTH_LONG).show();
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

    }
}



