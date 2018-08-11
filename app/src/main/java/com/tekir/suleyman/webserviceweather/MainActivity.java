package com.tekir.suleyman.webserviceweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String ID = "yourId from openweathermap.org";
    private String URL = "";
    private String sehir="";
    private EditText ed_sehir;
    private TextView tv_sonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_sehir = (EditText) findViewById(R.id.ed_sehir);
        tv_sonuc = (TextView) findViewById(R.id.tv_sonuc);
    }

    public void veriCek(View view)
    {
        sehir = ed_sehir.getText().toString().trim();
        URL = "q="+sehir+"&APPID="+ID;

        /*
        WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask(MainActivity.this);
        weatherAsyncTask.execute("http://api.openweathermap.org/data/2.5/forecast?q="+sehir+"&APPID="+ID);
        */

        WeatherRestClient.get(URL,null,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    tv_sonuc.setText(response.getJSONObject("city").getString("country"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
