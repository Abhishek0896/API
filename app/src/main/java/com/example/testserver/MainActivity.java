package com.example.testserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testserver.Models.Example;
import com.example.testserver.Models.Information;
import com.example.testserver.Models.Masscom;
import com.example.testserver.model.AuthApi;
import com.example.testserver.service.MyIntentService;
import com.example.testserver.utils.NetworkHelper;
import com.example.testserver.utils.RequestPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_OUTPUT = "LOG_OUTPUT";
    public static final String JSON_URL ="https://fortnite-api.theapinetwork.com/store/get/";
    public static final String JSON_GIS ="http://uatapi.upptax.org/api/configuration";
    public static final String MY_URL ="http://192.168.1.7/pakinfo/json/itemsfeed.php";
    TextView tvload;
    Button startbtn,btn;
    boolean isNetwork;
    List<Example> exampleList = new ArrayList<>();
    List<Information> information = new ArrayList<>();
    private final BroadcastReceiver mreciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Example data =(Example) intent.getParcelableExtra(MyIntentService.SERVICE_PAYLOAD);
//            exampleList=Arrays.asList(data);
//            Logoutput();
//            showData(data);
            AuthApi[] data = (AuthApi[]) intent.getParcelableArrayExtra(MyIntentService.SERVICE_PAYLOAD);
            for(AuthApi item:data){
                Logoutput(item.getCityname());
            }

        }
    };

//    private void showData(Masscom masscom) {
//        for (int i = 0; i < masscom.getData().getSurveyCities().size(); i++) {
//            tvload.append(masscom.getData().getSurveyCities().get(i).getName() + "\n");
//        }
//    }

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

            RequestPackage requestPackage = new RequestPackage();
            requestPackage.setEndpoint(MY_URL);
            requestPackage.setMethod("GET");
            requestPackage.setParams("province","Punjab");
            requestPackage.setParams("id","2");
            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            intent.putExtra(MyIntentService.SERVICE_REQUEST_PACKAGE,requestPackage);
//            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        }else{
            Toast.makeText(this,"Network not available...!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Logoutput(String name){

//        for(int i=0;i<information.size();i++)
            tvload.append(name+"\n");
//        try {
//            for(int i=0;i<exampleList.get(0).getData().size();i++){
//                tvload.append(""+exampleList.get(0).getData().get(i).getItem().getName()+"\n");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mreciever, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }
}