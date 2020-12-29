package com.example.testserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testserver.model.GIS;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_OUTPUT = "LOG_OUTPUT";
    public static final String JSON_URL ="http://uatapi.upptax.org/api/configuration";
    public static final String JSON_FORTNITE="https://fortnite-api.theapinetwork.com/store/get/";
    public static final String WEB_URL="https://jsonplaceholder.typicode.com/photos";
    TextView tvload;
    Button startbtn,btn;
    boolean isNetwork;
    private BroadcastReceiver mreciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            GIS gis = (GIS)intent.getParcelableExtra(MyIntentService.SERVICE_PAYLOAD);
//            for(int i =0;i<gis.getData().getSurveyCities().size();i++)
//                Logoutput( gis.getData().getSurveyCities().get(i).getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvload = findViewById(R.id.tvload);
        startbtn = findViewById(R.id.startbtn);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCode();
            }
        });
        isNetwork = NetworkHelper.isNetworkAvailable(this);
//        Logoutput("Network "+isNetwork);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                startActivity(intent);
            }
        });


    }

    public void runCode(){
        if(isNetwork){
            Intent intent = new Intent(MainActivity.this,MyIntentService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        }else{
            Toast.makeText(this,"Network not available...!!!", Toast.LENGTH_SHORT).show();
        }
    }
//
    public void Logoutput(String data){
        Log.d(LOG_OUTPUT,data);
        tvload.append(data+"\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mreciever, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }
}