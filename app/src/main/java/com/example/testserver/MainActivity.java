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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_OUTPUT = "LOG_OUTPUT";
    public static final String JSON_URL ="https://fortnite-api.theapinetwork.com/store/get/";
    TextView tvload;
    Button startbtn,btn;
    boolean isNetwork;
    List<Example> exampleList = new ArrayList<>();
    private final BroadcastReceiver mreciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Example data = (Example) intent.getParcelableExtra(MyIntentService.SERVICE_PAYLOAD);
            exampleList = Arrays.asList(data);
            Logoutput();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvload = findViewById(R.id.tvload);
        startbtn = findViewById(R.id.startbtn);
        btn = findViewById(R.id.btn);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCode();
            }
        });
        isNetwork = NetworkHelper.isNetworkAvailable(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)exampleList);
                intent.putExtra("BUNDLE",args);
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

    public void Logoutput(){
        try {
            for(int i=0;i<exampleList.get(0).getData().size();i++){
                tvload.append(""+exampleList.get(0).getData().get(i).getItem().getName()+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mreciever, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }
}