package com.example.rajan.wearther;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import Model.forcast;
import data.JSONWeatherPaeser;

import static android.R.attr.onClick;

public class field5 extends AppCompatActivity implements View.OnClickListener {
    public TextView tv;
    public Button b1;
    public String TAG=field5.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tv=(TextView)findViewById(R.id.textView3);
        tv.setText("das");
        b1=(Button)findViewById(R.id.button);
        String dsp="";
        JSONWeatherPaeser js=new JSONWeatherPaeser();
        int max=50;
        int day=10;
        try {
            forcast fs= js.getForcast();
            for(int i=0;i<5;i++){
                Log.e(TAG, "onClick: "+Float.toString(fs.getRain(i)));
                dsp+="day "+i+" "+Float.toString(fs.getRain(i))+"\n";
                if(max<(int)fs.getRain(i) && day>(i+1)){
                    max=(int)fs.getRain(i);
                    day=i+1;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, String.valueOf(e));
        }
        if(day==1){
            dsp+="\n\n\n\nwe highily recommend you to stop motor as today chance of rain is high";
        }

        if(day==2){
            dsp+="\n\n\n\nwe recommend you to stop motor as tommorow chance of rain is high";
        }

        if(day==3){
            dsp+="\n\n\n\nwe recomend you to stop motor as day after tommorow  chance of rain is high";
        }

        if(day==4){
            dsp+="\n\n\n\nu can choose to stop motor as after 3 days chance of rain is high";
        }

        dsp+="\nhappy farming";
        tv.setText(dsp);
        b1.setOnClickListener(field5.this);

    }
    @Override
    public void onClick(View v) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("9463097097", null, "stop", null, null);
        Toast.makeText(field5.this,"motor stop \n smart farming",Toast.LENGTH_LONG).show();
    }
}
