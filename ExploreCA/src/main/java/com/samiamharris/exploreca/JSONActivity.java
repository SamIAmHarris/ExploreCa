package com.samiamharris.exploreca;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by samharris on 2/11/14.
 */
public class JSONActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

    }

    public void createJSONFile(View v) throws IOException, JSONException {

        JSONArray data = new JSONArray();
        JSONObject tour;

        tour = new JSONObject();
        tour.put("tour", "SaltonSea");
        tour.put("price", 900);
        data.put(tour);

        tour = new JSONObject();
        tour.put("tour", "Death Valley");
        tour.put("price", 600);
        data.put(tour);

        tour = new JSONObject();
        tour.put("tour", "San Francisco");
        tour.put("price", 1200);
        data.put(tour);

        String text = data.toString();

        FileOutputStream fos = openFileOutput("tours", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();

        UIHelper.displayText(this, R.id.j_tv, "File Written to Disk:\n" + data.toString());

    }

    public void readJSONFile(View v) throws IOException, JSONException{
        //read content into memory
        FileInputStream fis = openFileInput("tours");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        //deserializing into JSON Array
        JSONArray data = new JSONArray(b.toString());

        //loop through JSOn Array and read the info
        StringBuffer toursBuffer = new StringBuffer();
        for(int i = 0; i <data.length(); i++) {
            String tour = data.getJSONObject(i).getString("tour");
            toursBuffer.append(tour + "\n");
        }

        UIHelper.displayText(this, R.id.j_tv, toursBuffer.toString());


    }
}
