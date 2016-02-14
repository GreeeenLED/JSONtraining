package com.example.atitude6430.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity implements asyncTask.resultJSON {

    TextView text;
    public void sendRequest(View view){
        asyncTask JSONtask = new asyncTask();
        JSONtask.data=this;//very important!!!!!!!!!!!
        JSONtask.execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void sendResultToMain(String output) {
        text.setText("otrzymano: "+output);
        Log.d("send result","main activity");
    }
}
