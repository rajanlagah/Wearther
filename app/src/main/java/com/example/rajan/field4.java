package com.example.rajan;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rajan.wearther.R;

import org.json.JSONException;

import Model.forcast;
import data.JSONWeatherPaeser;

public class field4 extends AppCompatActivity {

    public TextView tv;
    private static final String TAG = field4.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.e(TAG,"dada");
                tv=(TextView)findViewById(R.id.textView2);
                tv.setText("dadda");
                JSONWeatherPaeser js=new JSONWeatherPaeser();
                try {
                    forcast fs= js.getForcast();
                    for(int i=0;i<6;i++){
                        Log.e(TAG, "onClick: "+Float.toString(fs.getRain(i)));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Opas");
                }
            }
        });
    }

}
