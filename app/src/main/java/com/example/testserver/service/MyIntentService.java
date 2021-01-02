 package com.example.testserver.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.testserver.Models.Example;
import com.example.testserver.Models.Information;
import com.example.testserver.utils.HttpHelper;
import com.example.testserver.utils.RequestPackage;
import com.google.gson.Gson;

import java.io.IOException;


public class MyIntentService extends IntentService {


    public static final String SERVICE_PAYLOAD = "SERVICE_PAYLOAD";
    public static final String SERVICE_MESSAGE = "SERVICE_MESSAGE";
    public static final String SERVICE_REQUEST_PACKAGE = "SERVICE_REQUEST_PACKAGE";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        RequestPackage requestPackage=
                (RequestPackage) intent.getParcelableExtra(SERVICE_REQUEST_PACKAGE);


        Uri uri = intent.getData();
        String data;
        try {
            data = HttpHelper.downloadUrl(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        Information[] example= gson.fromJson(data,Information[].class);
        sendMessagetoUI(example);
    }

    private void sendMessagetoUI(Information[] data) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_PAYLOAD,data);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
//    private void sendMessagetoUI(Masscom data){
//        Intent intent = new Intent(SERVICE_MESSAGE);
//        intent.putExtra(SERVICE_PAYLOAD,data);
//        LocalBroadcastManager.getInstance(this)
//                .sendBroadcast(intent);
//    }
//
//    private void sendMessagetoUI(Example example){
//        Intent intent = new Intent(SERVICE_MESSAGE);
//        intent.putExtra(SERVICE_PAYLOAD,example);
//        LocalBroadcastManager.getInstance(this)
//                .sendBroadcast(intent);
//    }
}