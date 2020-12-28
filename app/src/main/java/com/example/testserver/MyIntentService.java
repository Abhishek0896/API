package com.example.testserver;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.testserver.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MyIntentService extends IntentService {


    public static final String SERVICE_PAYLOAD = "SERVICE_PAYLOAD";
    public static final String SERVICE_MESSAGE = "SERVICE_MESSAGE";
    public static final String SERVICE_EXCEPTION="SERVICE_EXCEPTION";


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
        User[] example= gson.fromJson(data, User[].class);
        sendMessagetoUI(example);
//        Log.d("TESTING",example.getData().getItemId());

    }

    private void sendMessagetoUI( User[] data) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(SERVICE_PAYLOAD,data);

        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(intent);
    }
}