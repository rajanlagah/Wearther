package com.example.rajan.wearther;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import Model.Weather;
import data.JSONWeatherPaeser;
import data.WeatherHttpClient;
import utility.utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    static Weather weather;
    public TextView tv;
    public boolean enter=false;
    public TextView tempDisplay;
    public TextView press;
    public TextView hummidity;
    public TextView WindView;
    public TextView cloud;
    public TextView res;
    public Button b1;
    public EditText ed;
    public String place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "onCreate: weather called" );
         //renderWeather(place);

        //ed=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.wind);
        // while(!enter){}
        //if(enter)
        tempDisplay=(TextView)findViewById(R.id.tempDisplay);
        press=(TextView)findViewById(R.id.pressureDisplay);
        hummidity=(TextView)findViewById(R.id.hummidityDisplay);
        WindView=(TextView)findViewById(R.id.windDisplay);
        cloud=(TextView)findViewById(R.id.cloudDisplay);
        res=(TextView)findViewById(R.id.result);
        b1=(Button)findViewById(R.id.irigate);

        b1.setOnClickListener(MainActivity.this);
       }

    public void renderWeather(String city) {
        WeatherTask we = new WeatherTask();
        we.execute(weather);
    }

    @Override
    public void onClick(View v) {
        ed=(EditText)findViewById(R.id.editText);
        place=ed.getText().toString();
        utils.setUrl(place);
        Log.e(TAG,place);
        Toast.makeText(this,place,Toast.LENGTH_LONG).show();

        Log.e(TAG,"fafs");
      //  SmsManager smsManager = SmsManager.getDefault();
        //smsManager.sendTextMessage("9463097097", null, "stop", null, null);
        renderWeather(place);
      //  Intent intent=new Intent("com.example.rajan.wearther.field5");
        //startActivity(intent);
        }

    private class WeatherTask extends AsyncTask<Weather, Weather, Weather> {

        @Override
        protected Weather doInBackground(Weather... params) {
            String data = ((new WeatherHttpClient()).getWeatherData());
            JSONWeatherPaeser js = new JSONWeatherPaeser();
            try {
                params[0] = js.getWeather();
                publishProgress(params[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //Log.v("data: ",weather.place.getCity());
            return params[0];

        }

        @Override
        protected void onProgressUpdate(Weather... values) {
                    Log.e(TAG,"good");
                    tempDisplay.setText(Float.toString(values[0].temp.getTemp()));
                    press.setText(Float.toString(values[0].cC.getPressure()));
                    hummidity.setText(Float.toString(values[0].cC.getHumidity()));
                    WindView.setText(Float.toString(values[0].wind.getSpeed()));
                    cloud.setText(Integer.toString(values[0].cloud.getPrespitation()));
                    res.setText(values[0].cC.getCondition());

                }
           }

        protected void onPostExecute(Weather weather){
            Log.v(TAG,"done");

            //  tv.setText((int) weather.place.getLat());
        enter=true;
        }

    /**
     * Created by rajan on 09-07-2017.
     */

    public static class field {
    }
}


