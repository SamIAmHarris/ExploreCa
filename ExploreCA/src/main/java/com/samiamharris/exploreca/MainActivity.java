package com.samiamharris.exploreca;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    public static final String USERNAME = "username";

    private SharedPreferences settings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating shared preferences object
        //getPreferences method - preferences local to current activity
        //getSharedPreferences - pass in string identifier. Shared through entire App
        settings = getPreferences(MODE_PRIVATE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setPreference(View v) {
        Log.d("TAG", "setPreferences");

        //Using shared preferences Editor to add SP
        SharedPreferences.Editor editor = settings.edit();
        String prefValue = UIHelper.getText(this, R.id.et_username);
        editor.putString(USERNAME, prefValue);
        editor.commit();
        //Displaying the shared preference on the screen
        UIHelper.displayText(this, R.id.tv_show, "Preference Saved");

    }


    public void refreshDisplay(View v) {
        Log.d("TAG", "refreshDisplay()");

        //pass in the String you want, and default value to show if the String isn't there
        String prefValue = settings.getString(USERNAME, "Not found");
        UIHelper.displayText(this,R.id.tv_show, prefValue);
    }



}
