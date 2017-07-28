package data;

import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Model.Weather;
import utility.utils;

/**
 * Created by rajan on 02-07-2017.
 */

public class WeatherHttpClient {
    EditText ts;
    String TAG= WeatherHttpClient.class.getSimpleName();

    public String getWeatherData() {


        HttpURLConnection connection = null;
        InputStream inputStream = null;
       // String url="http://api.openweathermap.org/data/2.5/forecast?q="+place+"&APPID=f6d677adaf20f56d3a78d970512b2c39&units=metric";
        String url=utils.getUrl();
      // Log.e(TAG,place);
        String tag = WeatherHttpClient.class.getSimpleName();
        try {
            connection = ((HttpURLConnection) (new URL(url)).openConnection());
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();

            int code = connection.getResponseCode();
            Log.e(tag, String.valueOf(code));

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
