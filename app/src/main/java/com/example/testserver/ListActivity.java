package com.example.testserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.testserver.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity  {


    public static final String TAG = "mytag";
    private List<User> mDataList;
    private RecyclerView recyle;
    //    private CityDataSource mDataSource;
    private RawAdapter mDataAdapter;
    private Map<String, Bitmap> mBitmaps;
    public static final String WEB_URL = "https://jsonplaceholder.typicode.com/photos";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(MyIntentService.SERVICE_PAYLOAD)) {
                User[] cityItems = (User[])
                        intent.getParcelableArrayExtra(MyIntentService.SERVICE_PAYLOAD);

                mDataList = Arrays.asList(cityItems);
                Toast.makeText(context, "Items Downloaded: " + mDataList.size(), Toast.LENGTH_SHORT).show();
//                mBitmaps = bitmapMap;
                showData();

//                getSupportLoaderManager().initLoader(0, null, ListActivity.this)
//                        .forceLoad();

                Log.d(TAG, "onReceive: called");
            }
//            else if (intent.hasExtra(MyIntentService.SERVICE_EXCEPTION)) {
//                String message = intent.getStringExtra(MyIntentService.SERVICE_EXCEPTION);
//
//                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyle = findViewById(R.id.recyle);
        recyle.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = new Intent(ListActivity.this, MyIntentService.class);
        intent.setData(Uri.parse(WEB_URL));
        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    public void showData(){
        mDataAdapter = new RawAdapter(ListActivity.this, mDataList);
        recyle.setAdapter(mDataAdapter);
    }

//    @NonNull
//    @Override
//    public Loader<Map<String, Bitmap>> onCreateLoader(int id, @Nullable Bundle args) {
//        return new MyImageTask(this, mDataList);
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<Map<String, Bitmap>> loader, Map<String, Bitmap> bitmapMap) {
//
//        mBitmaps = bitmapMap;
//        mDataAdapter = new RawAdapter(this, mDataList);
//        recyle.setAdapter(mDataAdapter);
////        showRecyclerData(null);
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<Map<String, Bitmap>> loader) {
//
//    }
//
//    private static class MyImageTask extends AsyncTaskLoader<Map<String, Bitmap>> {
//
//        //        private static final String PHOTO_BASE_URL="http://10.0.2.2/pakinfo/images/";
//        private static List<User> mCityList;
//
//
//        public MyImageTask(@NonNull Context context, List<User> cityItems) {
//            super(context);
//            mCityList = cityItems;
//        }
//
//        @Nullable
//        @Override
//        public Map<String, Bitmap> loadInBackground() {
//
//            Map<String, Bitmap> map = new HashMap<>();
//
//            for (int i = 0; i < 10; i++) {
////                String imageurl=PHOTO_BASE_URL+item.getImage();
//
//                InputStream inputStream = null;
//
//                try {
//                    URL imageUrl = new URL("https://dropin-bucket.mativecdn.com/cosmetics/br/cid_679_athena_commando_m_modernmilitaryeclipse/icon.png");
//                    inputStream = (InputStream) imageUrl.getContent();
//                    Bitmap bitmap;
//                    bitmap = BitmapFactory.decodeStream(inputStream);
//                    Bitmap bitmap1;
//                    bitmap1 = bitmap;
//                    map.put(mCityList.get(i).getTitle(), bitmap);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (inputStream != null) {
//                        try {
//                            inputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }
//            return map;
//        }
//    }

}