package com.tekir.suleyman.webserviceweather;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by suleyman on 8/11/2018.
 */

public class WeatherAsyncTask extends AsyncTask<String,Void,String> {
    private TextView tv;

    public WeatherAsyncTask(Context context) {
        tv = (TextView) ( (Activity) context).findViewById(R.id.tv_sonuc);
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        StringBuilder sb = new StringBuilder();
        URL url1;
        HttpURLConnection httpURLConnection;
        BufferedReader br;
        try {
            url1 = new URL(url);
            httpURLConnection = (HttpURLConnection) url1.openConnection();
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = "";
            while ( (line = br.readLine()) != null )
            {
                sb.append(line).append("\n");
            }
            return sb.toString().trim();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        tv.setText(s);
    }
}
