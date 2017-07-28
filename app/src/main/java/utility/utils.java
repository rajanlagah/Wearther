package utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajan on 02-07-2017.
 */

public class utils {

    public static String Base_URL="";
    public static String IMG_URL="api.openweathermap.org/img/w/";

    public static void setUrl(String place){
        Base_URL="http://api.openweathermap.org/data/2.5/forecast?q="+place+"&APPID=f6d677adaf20f56d3a78d970512b2c39&units=metric";
    }

    public static String getUrl(){
        return    Base_URL;
    }

    public static JSONObject getObject(String tagName,JSONObject jObj) throws JSONException {
        JSONObject jo=jObj.getJSONObject(tagName);
        return jo;
    }

    public static JSONArray getJSONArray(String tagName,JSONObject job) throws JSONException {
        return job.getJSONArray(tagName);
    }

    public static String getString(String tagName,JSONObject jObj) throws JSONException {
        return jObj.getString(tagName) ;
    }

    public static String setString(String tagName,JSONObject jObj) throws JSONException {
        return jObj.getString(tagName) ;
    }
    public static float getFloat(String tagName,JSONObject jObj) throws JSONException {
        return (float)jObj.getDouble(tagName);
    }

    public static float setFloat(String tagName,JSONObject jObj) throws JSONException {
        return (float)jObj.getDouble(tagName);
    }
    public static int getInt(String tagName,JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
    public static long getlong(String tagName,JSONObject jObj) throws JSONException {
        return  jObj.getLong(tagName);
    }
}
