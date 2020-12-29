package com.example.testserver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rawadapter extends RecyclerView.Adapter<Rawadapter.MyViewHolder> {
    Map<String, Bitmap> drawable = new HashMap<>();
    List<Example> data = new ArrayList<>();
    Context context;

    public Rawadapter( List<Example> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;
        v = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Example example = data.get(0);
        holder.uname.setText(example.getData().get(position).getItem().getName());
//        if (drawable.containsKey(example.getData().get(position).getItem().getName())) {
//            holder.uimg.setImageBitmap(drawable.get(example.getData().get(position).getItem().getName()));
//        } else {
//            MyImageTask task = new MyImageTask();
//            task.setViewHolder(holder);
//            task.execute(example);
//        }

        try{
            Bitmap bitmap = CacheImageManager.getImage(context,example.getData().get(position).getItem().getName());
            if(bitmap == null){
                MyImageTask task = new MyImageTask();
                task.setViewHolder(holder);
                task.execute(example);
            }else{
                holder.uimg.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return data.get(0).getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView uname;
        ImageView uimg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uimg = itemView.findViewById(R.id.uimg);
            uname = itemView.findViewById(R.id.uname);
        }
    }


    class MyImageTask extends AsyncTask<Example, Void, Bitmap> {
        private Example muser;
        private MyViewHolder myViewHolder;

        public void setViewHolder(MyViewHolder myViewHolder) {
            this.myViewHolder = myViewHolder;
        }

        @Override
        protected Bitmap doInBackground(Example... users) {
            Bitmap bitmap = null;
            muser = users[0];
            InputStream inputStream = null;

            try {
                URL imageUrl = new URL(muser.getData().get(myViewHolder.getAdapterPosition()).getItem().getImages().getIcon());
                inputStream = (InputStream) imageUrl.getContent();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            try {
                myViewHolder.uimg.setImageBitmap(bitmap);
            CacheImageManager.putImage(context,muser.getData().get(myViewHolder.getAdapterPosition()).getItem().getName(),bitmap);
                drawable.put(muser.getData().get(myViewHolder.getAdapterPosition()).getItem().getName(), bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
