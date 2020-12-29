package com.example.testserver;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.Gson;

import java.io.IOException;


public class MyIntentService extends IntentService {


    public static final String SERVICE_PAYLOAD = "SERVICE_PAYLOAD";
    public static final String SERVICE_MESSAGE = "SERVICE_MESSAGE";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Uri uri = intent.getData();
        String data;
        try {
            data = HttpHelper.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Gson gson = new Gson();
        Example example= gson.fromJson(data,Example.class);
        Log.d("TESTING",example.getData().get(0).getItemId());
        sendMessagetoUI(example);


    }

    private void sendMessagetoUI( Example data) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_PAYLOAD,data);

        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
}