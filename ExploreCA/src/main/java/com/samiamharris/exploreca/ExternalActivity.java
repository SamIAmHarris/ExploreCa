package com.samiamharris.exploreca;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by samharris on 2/11/14.
 */
public class ExternalActivity extends Activity {

    private File file;
    private static final String FILENAME = "jsondata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_external);

        File extDir = getExternalFilesDir(null);
        String path = extDir.getAbsolutePath();
        UIHelper.displayText(this, R.id.ex_tv, path);

        file = new File(extDir, FILENAME);

    }

    public void createExternalFile(View v) throws IOException, JSONException{

        if(!checkExternalStorage()) {
            return;
        }

        JSONArray data = getNewJSONData();

        String text = data.toString();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(text.getBytes());
        fos.close();

        UIHelper.displayText(this, R.id.ex_tv, "File written to disk:\n" + data.toString());
    }

    public void readExternalFile(View v) throws IOException, JSONException{

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        JSONArray data = new JSONArray(b.toString());

        StringBuffer toursBuffer = new StringBuffer();
        for(int i = 0; i <data.length(); i++) {
            String tour = data.getJSONObject(i).getString("tour");
            toursBuffer.append(tour + "\n");
        }

        UIHelper.displayText(this, R.id.ex_tv, "File read from disk:\n" + toursBuffer.toString());


    }

    private JSONArray getNewJSONData() throws JSONException {
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

        return data;
    }

    public boolean checkExternalStorage() {

        String state = Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            UIHelper.displayText(this, R.id.ex_tv, "External Storage is Read Only");
        } else {
            UIHelper.displayText(this, R.id.ex_tv, "External Storage is Unavailable");

        }
        return false;
    }
}
