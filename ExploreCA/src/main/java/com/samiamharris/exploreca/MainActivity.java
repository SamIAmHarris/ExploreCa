package com.samiamharris.exploreca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File f = getFilesDir();
        String path = f.getAbsolutePath();
        UIHelper.displayText(this, R.id.bottom_tv, path);

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

    public void createFile(View v) throws IOException {
        String text = UIHelper.getText(this, R.id.top_et);
        FileOutputStream fos = openFileOutput("myfile.txt", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        UIHelper.displayText(this, R.id.bottom_tv, "File Written To Disk");

    }

    public void readFile(View v) throws IOException{
        FileInputStream fis = openFileInput("myfile.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        UIHelper.displayText(this, R.id.bottom_tv, b.toString());
        bis.close();
        fis.close();
    }

    public void goToJSON(View v) {
        Intent JIntent = new Intent(this, JSONActivity.class);
        startActivity(JIntent);
    }

}
