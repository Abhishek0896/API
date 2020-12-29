package com.example.testserver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testserver.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RawAdapter extends RecyclerView.Adapter<RawAdapter.MyViewHolder> {
    Context context;
    List<User> users = new ArrayList<>();
    Map<String, Bitmap> drawable = new HashMap<>();

    public RawAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;
        v = LayoutInflater.from(context).inflate(R.layout.raw_layout, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User myusers = users.get(position);
        holder.uname.setText(users.get(position).getTitle());
//        if (drawable.containsKey(users.get(position).getTitle())) {
//            holder.uimg.setImageBitmap(drawable.get(users.get(position).getTitle()));
//        }else{
//            MyImageTask task = new MyImageTask();
//            task.setViewHolder(holder);
//            task.execute(myusers);
//        }
        try{
            Bitmap bitmap = CacheImageManager.getImage(context,myusers);
            if(bitmap == null){
                MyImageTask task = new MyImageTask();
                task.setViewHolder(holder);
                task.execute(myusers);
            }else{
                holder.uimg.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 10;
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

    class MyImageTask extends AsyncTask<User, Void, Bitmap> {
        private User muser;
        private MyViewHolder myViewHolder;

        public void setViewHolder(MyViewHolder myViewHolder) {
            this.myViewHolder = myViewHolder;
        }

        @Override
        protected Bitmap doInBackground(User... users) {
            Bitmap bitmap = null;
            muser = users[0];
            InputStream inputStream = null;

            try {
                URL imageUrl = new URL("https://dropin-bucket.mativecdn.com/cosmetics/br/cid_679_athena_commando_m_modernmilitaryeclipse/icon.png");
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
            myViewHolder.uimg.setImageBitmap(bitmap);
            CacheImageManager.putImage(context,muser,bitmap);
//            drawable.put(muser.getTitle(), bitmap);
        }
    }
}
