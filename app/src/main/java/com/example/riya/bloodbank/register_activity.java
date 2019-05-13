package com.example.riya.bloodbank;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class
register_activity extends ActionBarActivity implements View.OnClickListener {

    EditText name,  contactno, address, city,  age, username, password;
    Spinner bloodgroup,gender;
    String availability,x1;
    AlertDialog alertDialog;
    private  static final int RESULT_LOAD_IMAGE=1;
    private  static final int RESULT_LOAD_IMAGE1=1;
    ImageView imagetoupload;
    private  static  final  String SERVER_ADDRES="http://192.168.43.231/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

    imagetoupload=(ImageView)findViewById(R.id.imageView);
        name=(EditText)findViewById(R.id.enter_name);
        bloodgroup=(Spinner)findViewById(R.id.spinner);
        contactno=(EditText)findViewById(R.id.editText2);
        address=(EditText)findViewById(R.id.editText3);
        city=(EditText)findViewById(R.id.editText4);
        gender=(Spinner)findViewById(R.id.spinner1);
        age=(EditText)findViewById(R.id.editText8);
        username=(EditText)findViewById(R.id.editText6);
        password=(EditText)findViewById(R.id.editText7);
imagetoupload.setOnClickListener(this);



    }
    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) findViewById(R.id.c1);
        if(checkBox.isChecked()){
        x1="yes";

        }
        else{x1="no";}
    }

    public  void OnReg(View view){
            String s="Field is required!!";
        //name = (EditText)findViewById(R.id.first_name);
        if(name.getText().toString().length() == 0 || contactno.getText().toString().length() == 0 || address.getText().toString().length() == 0 || city.getText().toString().length() == 0 || age.getText().toString().length() == 0 || username.getText().toString().length() == 0 || password.getText().toString().length() == 0 || password.getText().toString().length()<=8|| password.getText().toString().length()>=16) {


            if(name.getText().toString().length() == 0 ) {
                name.setError("requires");
            }

          /*  if(bloodgroup.getSelectedItem().toString().length() == 0 ) {
                bloodgroup.set("requires");
            }*/

            if(contactno.getText().toString().length() == 0 ) {
                contactno.setError("requires");
            }

            if(address.getText().toString().length() == 0 ) {
                address.setError("requires");
            }

            if(city.getText().toString().length() == 0 ) {
                city.setError("requires");
            }

          /*  if(gender.getText().toString().length() == 0 ) {
                gender.setError("requires");
            }*/

            if(age.getText().toString().length() == 0 ) {
                age.setError("requires");
            }

            if(username.getText().toString().length() == 0 ) {
                username.setError("requires");
            }

            if(password.getText().toString().length() == 0 || password.getText().toString().length()<=8|| password.getText().toString().length()>=16 ) {
                password.setError("requires or length must be between 8 to 16 ");
            }

        }
        else {
            String str_name = name.getText().toString();
            String str_bloodgroup = bloodgroup.getSelectedItem().toString();
            String str_contactno = contactno.getText().toString();
           // int str_contactno = Integer.parseInt(contactno.getText().toString());
            String str_address = address.getText().toString();
            String str_city = city.getText().toString();
            String str_gender = gender.getSelectedItem().toString();
            String str_age = age.getText().toString();
            String str_username = username.getText().toString();
            String str_password = password.getText().toString();
            if(x1.equals("yes"))
            {
                availability="yes";
            }
            else if(x1.equals("no"))
            {
                availability="no";
            }
            String str_availability = availability;


            //name.setText("");
           // contactno.setText("");
            //address.setText("");
            //city.setText("");
            //age.setText("");
            //username.setText("");
            //password.setText("");
            String type = "register";

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_name, str_bloodgroup, str_contactno, str_address, str_city, str_gender, str_age, str_username, str_password,str_availability);
        }
    }
    public void uploadImage(View v) {
      /*  Intent j = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(j, RESULT_LOAD_IMAGE);
        Bitmap image = ((BitmapDrawable) imagetoupload.getDrawable()).getBitmap();*/
    }

    public  void uploadPdf(View view)
    {

      //Intent i=new Intent(Intent.ACTION_PICK_ACTIVITY);
        //Intent i1=new Intent(Intent.ACTION_GET_CONTENT);
        //i1.setType("*/pdf");
        Intent i1=new Intent(Intent.ACTION_PICK);
        Intent i2=new Intent("android.media.action.FILE_CAPTURE");
        i1.putExtra(Intent.EXTRA_INTENT,i2);
        //i.putExtra(Intent.EXTRA_INTENT,i1);
        //i.putExtra(Intent.EXTRA_TITLE, "select source");

        startActivityForResult(i1,RESULT_LOAD_IMAGE1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && requestCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            String[]projection={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(selectedimage,projection,null,null,null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(projection[0]);
            String filepath=cursor.getString(columnIndex);
            cursor.close();
            Bitmap ysi= BitmapFactory.decodeFile(filepath);
            Drawable d=new BitmapDrawable(ysi);
            imagetoupload.setBackground(d);
            //  String[]projection={MediaStore.Images.Media.DATA};
          //  imagetoupload.setImageURI(selectedimage);
        //    imagetoupload.setImageBitmap();

        }
        if (requestCode == RESULT_LOAD_IMAGE1 && requestCode == RESULT_OK && data != null) {
            Uri selectedimage = data.getData();
            //  String[]projection={MediaStore.Images.Media.DATA};
            Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();

        }
    }
   // @Override
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && requestCode==RESULT_OK && data != null)
        {
            Uri selectedimage=data.getData();
            imagetoupload.setImageURI(selectedimage);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second_activity, menu);
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

    @Override
    public void onClick(View v)

    {
        switch (v.getId()) {
            case R.id.imageView:


            Intent j = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(j, RESULT_LOAD_IMAGE);
                break;
            case R.id.button5:
                Bitmap image = ((BitmapDrawable) imagetoupload.getDrawable()).getBitmap();
                new UploadImage(image, username.getText().toString()).execute();
                break;
        }
    }

    private class UploadImage extends AsyncTask<Void,Void,Void>
    {

        Bitmap image;
        String name;
        public UploadImage(Bitmap image,String name)
        {
            this.image=image;
            this.name=name;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodedImage=Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
            ArrayList<NameValuePair> datatosend=new ArrayList<>();
            datatosend.add(new BasicNameValuePair("image",encodedImage));
            datatosend.add(new BasicNameValuePair("name",name));

            HttpParams httpRequestParams=getHttpRequestParams();
            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRES +"SavePicture.php");
            try {
            post.setEntity(new UrlEncodedFormEntity(datatosend));
                client.execute(post);


            }

            catch (Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
    Toast.makeText(getApplicationContext(),"image uploaded",Toast.LENGTH_LONG).show();
        }
    }
    private HttpParams getHttpRequestParams()
    {
       HttpParams httpRequestParams=new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return  httpRequestParams;
    }
}
