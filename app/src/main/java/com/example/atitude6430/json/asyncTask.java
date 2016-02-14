package com.example.atitude6430.json;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Atitude6430 on 2016-02-14.
 */
public class asyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection=null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJSON = buffer.toString();
            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray arrayJSON = parentObject.getJSONArray("movies");
            JSONObject finalObject = arrayJSON.getJSONObject(0);

            String name = finalObject.getString("movie");
            Integer year = finalObject.getInt("year");

            return name+" "+year;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null) {
                connection.disconnect();
            }

            try {
                if (reader!=null){
                    reader.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public resultJSON data= null;
    public interface resultJSON{
        public void sendResultToMain(String output);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("Koniec","post execute "+result);
        data.sendResultToMain(result);
    }


}
