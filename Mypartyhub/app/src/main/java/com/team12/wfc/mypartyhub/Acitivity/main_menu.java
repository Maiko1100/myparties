package com.team12.wfc.mypartyhub.Acitivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.team12.wfc.mypartyhub.R;

public class main_menu extends ActionBarActivity implements View.OnClickListener{
    private static Button btnPostScreen;
    private static Button btnGetScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        btnPostScreen = (Button) findViewById(R.id.btnPostScreen);
        btnGetScreen = (Button) findViewById(R.id.btnGetScreen);
        btnPostScreen.setOnClickListener(this);
        btnGetScreen.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btnPostScreen:
                Intent intentPost = new Intent(this, postactivity.class);
                startActivity(intentPost);
                break;

            case R.id.btnGetScreen:
                Intent intentGet = new Intent(this, MainActivity.class);
                startActivity(intentGet);
                break;
        }
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
}
