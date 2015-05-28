package com.team12.wfc.mypartyhub.Acitivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.team12.wfc.mypartyhub.Models.Foto;
import com.team12.wfc.mypartyhub.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class Fotogalery extends ActionBarActivity implements View.OnClickListener {
    List<Foto> fotos= null;
    Button getPictures;
    GridView grid;
    String[] web = {
            "Google",
            "Github",
            "Instagram",
            "Facebook",
            "Flickr",
            "Pinterest",
            "Quora",
            "Twitter",
            "Vimeo",
            "WordPress",
            "Youtube",
            "Stumbleupon",
            "SoundCloud",
            "Reddit",
            "Blogger"

    } ;

    String[] images = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotogalery);
        //getPictures = (Button) findViewById(R.id.btnGetPictures);
        execute();
//        getPictures.setOnClickListener(this);
//        System.out.println(fotos.get(1).getFotoNaam());

        grid=(GridView)findViewById(R.id.grid);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Fotogalery.this, "You Clicked at " + web[position], Toast.LENGTH_SHORT).show();

            }
        });


    }




    public void execute() {
        new HttpAsyncTask().execute("http://10.0.3.2:8080/myparties/resources/photos/");
    }

    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btnGetPictures:
//                execute();
//                break;


        }
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                Gson gson = new Gson();
                String jsonOutput = result;
                Type listType = new TypeToken<List<Foto>>(){}.getType();
                fotos = (List<Foto>) gson.fromJson(jsonOutput, listType);
            }catch(Exception e){
                System.out.println("failed to create json object");
            }

            for (int i =0 ; i<fotos.size() ;i++){
                System.out.println(fotos.get(i).getFotoNaam().toString());
            }

            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
//            for (int i =0 ; i<fotos.size() ;i++){
//                images[i]=fotos.get(i).getFoto().toString();
//
//            }
            images[0]=fotos.get(0).getFoto();
            CustomGrid adapter = new CustomGrid(Fotogalery.this, web, images);

            grid.setAdapter(adapter);


        }
    }
}
