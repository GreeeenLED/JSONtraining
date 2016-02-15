package com.example.atitude6430.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements asyncTask.resultJSON {

    TextView text;
    ListView lista;
    public void sendRequest(View view){
        asyncTask JSONtask = new asyncTask();
        JSONtask.data=this;//very important!!!!!!!!!!!
        JSONtask.execute("http://api.androidhive.info/contacts/");
        //JSONtask.execute("http://jsonparsing.parseapp.com/jsonData/moviesDemoItem.txt");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btw how GIT works
        text = (TextView) findViewById(R.id.textView);
        lista = (ListView)findViewById(R.id.listView);
    }

    @Override
    public void sendResultToMain(List<String> output) {
        //for (int i=0;i<output.size();i++)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,output);
        lista.setAdapter(adapter);
           // text.setText("otrzymano: "+output);
        Log.d("send result","main activity");
    }
}
