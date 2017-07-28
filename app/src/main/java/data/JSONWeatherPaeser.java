package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Cloud;
import Model.Place;
import Model.Temperature;
import Model.Weather;
import Model.Wind;
import Model.currentCondition;
import Model.forcast;
import utility.utils;

import static android.content.ContentValues.TAG;

/**
 * Created by rajan on 02-07-2017.
 */

public class JSONWeatherPaeser {
    public String data="";
    public forcast fc;
    public forcast getForcast()throws  JSONException{

     return fc;
    }
    public Weather getWeather() throws JSONException {
        Weather weather = new Weather();
        fc=new forcast();
        data = new WeatherHttpClient().getWeatherData();
        Log.e(TAG, "getWeather: " + data);
        JSONObject jsonObject = new JSONObject(data);
        if(jsonObject!=null){
            JSONArray array=jsonObject.getJSONArray("list");
            Log.e(TAG,"sd"+data);
            for(int i=0;i<5;i++){
                JSONObject js=array.getJSONObject(i).getJSONObject("clouds");
                fc.setArr(i,utils.getFloat("all",js));
            }
        }
            if (jsonObject != null) {
                //place
                //JSONObject coordObj = utils.getObject("cod", jsonObject);
                //weather.place.setLat(utils.getInt("cod",jsonObject ));
                //place.setLon(utils.getFloat("lon", coordObj));
                //Log.e(TAG, "getWeather: "+place.getLat());

                JSONArray jsonArray=jsonObject.getJSONArray("list");
                JSONObject today=jsonArray.getJSONObject(0);

                JSONObject todayDataMain=today.getJSONObject("main");
                Temperature temperature=new Temperature();
                temperature.setTemp(utils.getFloat("temp",todayDataMain));
                weather.temp=temperature;

                currentCondition cC=new currentCondition();
                cC.setHumidity(utils.setFloat("humidity",todayDataMain));
                cC.setPressure(utils.setFloat("pressure",todayDataMain));
               JSONArray todayResult=today.getJSONArray("weather");
                JSONObject todayWeather=todayResult.getJSONObject(0);

                cC.setCondition(utils.getString("description",todayWeather));

                weather.cC=cC;

                JSONObject todayWind=today.getJSONObject("wind");
                Wind wind=new Wind();
                wind.setSpeed(utils.getFloat("speed",todayWind));

                JSONObject todayCloud=today.getJSONObject("clouds");
                Cloud cloud=new Cloud();
                cloud.setPrespitation(utils.getInt("all",todayCloud));
                weather.cloud=cloud;



            }

        return weather;
    }
}
