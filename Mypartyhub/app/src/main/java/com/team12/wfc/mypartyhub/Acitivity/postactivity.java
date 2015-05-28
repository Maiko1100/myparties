package com.team12.wfc.mypartyhub.Acitivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.team12.wfc.mypartyhub.Models.Foto;
import com.team12.wfc.mypartyhub.R;


public class postactivity extends Activity implements OnClickListener {

        EditText etName,etFeestId,etGebruikersnaam;
        Button btnPost;

        Foto foto;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_postactivity);

            etName = (EditText) findViewById(R.id.etName);
            etFeestId = (EditText) findViewById(R.id.etFeestId);
            btnPost = (Button) findViewById(R.id.btnPost);
            etGebruikersnaam = (EditText) findViewById(R.id.etGebruikersnaam);


            // add click listener to Button "POST"
            btnPost.setOnClickListener(this);

        }

        public static String POST(String url, Foto foto){
            InputStream inputStream = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                String json = "";

                // 3. build jsonObject
                JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("fotoNaam", foto.getFotoNaam());
                jsonObject.accumulate("foto", foto.getFoto());
                jsonObject.accumulate("feestId", foto.getFeestId());
                jsonObject.accumulate("gebruikersnaam", foto.getGebruikersnaam());
                // 4. convert JSONObject to JSON to String
                json = jsonObject.toString();

                // ** Alternative way to convert Person object to JSON string usin Jackson Lib
                // ObjectMapper mapper = new ObjectMapper();
                // json = mapper.writeValueAsString(person);

                // 5. set json to StringEntity
                StringEntity se = new StringEntity(json);

                // 6. set httpPost Entity
                httpPost.setEntity(se);

                // 7. Set some headers to inform server about the type of the content
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");

                // 8. Execute POST request to the given URL
                HttpResponse httpResponse = httpclient.execute(httpPost);

                // 9. receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // 10. convert inputstream to string
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";

            } catch (Exception e) {
//                Log.d("InputStream", e.getLocalizedMessage());
            }

            // 11. return result
            return result;
        }

        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.btnPost:
                    System.out.println("gedrukt:)");
                    if(!validate())
                        Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                    // call AsynTask to perform network operation on separate thread
                    new HttpAsyncTask().execute("http://10.0.3.2:8080/myparties/resources/photos/receivepicture");
                    break;
            }

        }
    public String test(Context context){
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.henk);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 1, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);


        return  encodedImage;
    }
        private class HttpAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... urls) {

                foto = new Foto();
                foto.setFotoNaam(etName.getText().toString());
                foto.setFeestId((Integer.parseInt(etFeestId.getText().toString())));
                foto.setGebruikersnaam(etGebruikersnaam.getText().toString());
                foto.setFoto(test(getApplicationContext()));
                return POST(urls[0], foto);
            }
            // onPostExecute displays the results of the AsyncTask.
            @Override
            protected void onPostExecute(String result) {
                Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
            }
        }

        private boolean validate(){
            if(etName.getText().toString().trim().equals(""))
                return false;

            else
                return true;
        }
        private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }
    }