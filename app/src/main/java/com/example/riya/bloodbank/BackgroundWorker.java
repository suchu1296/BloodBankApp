package com.example.riya.bloodbank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
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




import javax.net.ssl.HttpsURLConnection;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
public static  String result1;
    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://192.168.43.231/login2.php";
        String register_url = "http://192.168.43.231/register1.php";
        String search_url="http://192.168.43.231/finddonor.php";
        String edit1_url="http://192.168.43.231/edit1.php";
        if (type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
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
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;

                }

                bufferedReader.close();
                inputStream.close();

                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (type.equals("register"))
        {
            try {
                String name = params[1];
                String bloodgroup = params[2];
                String contactno = params[3];
                String address = params[4];
                String city = params[5];
                String gender = params[6];
                String age = params[7];
                String username = params[8];
                String password = params[9];
                String availability=params[10];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String post_data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode(name, "utf-8") + "&"
                +URLEncoder.encode("bloodgroup", "utf-8") + "=" + URLEncoder.encode(bloodgroup, "utf-8") + "&"
                +URLEncoder.encode("contactno", "utf-8") + "=" + URLEncoder.encode(contactno, "utf-8") + "&"
                +URLEncoder.encode("address", "utf-8") + "=" + URLEncoder.encode(address, "utf-8") + "&"
                +URLEncoder.encode("city", "utf-8") + "=" + URLEncoder.encode(city, "utf-8") + "&"
                +URLEncoder.encode("gender", "utf-8") + "=" + URLEncoder.encode(gender, "utf-8") + "&"
                +URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode(age, "utf-8") + "&"
                        +URLEncoder.encode("username", "utf-8") + "=" + URLEncoder.encode(username, "utf-8") + "&"
                        + URLEncoder.encode("password", "utf-8") + "=" + URLEncoder.encode(password, "utf-8")+"&"
                        + URLEncoder.encode("availability", "utf-8") + "=" + URLEncoder.encode(availability, "utf-8");

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

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (type.equals("search"))

        {
            try {
                String bloodgroup = params[1];
                String city=params[2];

                URL url = new URL(search_url);


                // Toast.makeText(context,"before connection",Toast.LENGTH_SHORT);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String post_data = URLEncoder.encode(" bloodgroup", "utf-8") + "=" + URLEncoder.encode( bloodgroup, "utf-8") + "&"
                        +URLEncoder.encode("city", "utf-8") + "=" + URLEncoder.encode(city, "utf-8");


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

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("edit"))
        {
            try {
                String name = params[1];
                String bloodgroup = params[2];
                String contactno = params[3];
                String address = params[4];
                String city = params[5];
                String gender = params[6];
                String age = params[7];
                //String username = params[8];
                String password = params[9];


                URL url = new URL(edit1_url);


                // Toast.makeText(context,"before connection",Toast.LENGTH_SHORT);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
                String post_data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode(name, "utf-8") + "&"
                        +URLEncoder.encode("bloodgroup", "utf-8") + "=" + URLEncoder.encode(bloodgroup, "utf-8") + "&"
                        +URLEncoder.encode("contactno", "utf-8") + "=" + URLEncoder.encode(contactno, "utf-8") + "&"
                        +URLEncoder.encode("address", "utf-8") + "=" + URLEncoder.encode(address, "utf-8") + "&"
                        +URLEncoder.encode("city", "utf-8") + "=" + URLEncoder.encode(city, "utf-8") + "&"
                        +URLEncoder.encode("gender", "utf-8") + "=" + URLEncoder.encode(gender, "utf-8") + "&"
                        +URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode(age, "utf-8") + "&"
                        + URLEncoder.encode("password", "utf-8") + "=" + URLEncoder.encode(password, "utf-8");

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

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }


    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login status");

    }

    @Override
    protected void onPostExecute(String result) {
        String message="login success!!!!!Welcome user";
        alertDialog.setTitle("Status");
        //String result1="hii";
       result1=result;
        alertDialog.setMessage(result);
        alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}