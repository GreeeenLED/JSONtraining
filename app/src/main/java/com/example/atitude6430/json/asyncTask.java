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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atitude6430 on 2016-02-14.
 */
public class asyncTask extends AsyncTask<String, Void, List<String>> {

    @Override
    protected List<String> doInBackground(String... params) {
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

            List<String> nameList = new ArrayList<String>();

            String finalJSON = buffer.toString();
            JSONObject parentObject = new JSONObject(finalJSON);
            JSONArray arrayJSON = parentObject.getJSONArray("contacts");
            //Log.d("array","caly array: "+arrayJSON.toString());
            //Log.d("array", "length: " + arrayJSON.length());
            JSONObject finalObject = null;
            for (int i = 0 ;i<arrayJSON.length();i++){
                finalObject = arrayJSON.getJSONObject(i);
                nameList.add(finalObject.getString("name") + " " + finalObject.getJSONObject("phone").getString("home"));
               // Log.d("ID", "added: " + nameList.get(i));
            }
            for (int i = 0 ;i<nameList.size();i++){
                Log.d("ID","pobrane: "+nameList.get(i));
            }
            //String name = finalObject.getString("name");
            //String year = finalObject.getString("gender");
            //return name+" "+year;
            return nameList;

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
        public void sendResultToMain(List<String> output);
    }

    @Override
    protected void onPostExecute(List<String> result) {
        Log.d("Koniec","post execute "+result);
        data.sendResultToMain(result);
    }


}
