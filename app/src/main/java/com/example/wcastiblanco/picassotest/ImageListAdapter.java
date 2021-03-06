package com.example.wcastiblanco.picassotest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

/**
 * Created by w.castiblanco on 14/10/2016.
 */
public class ImageListAdapter extends  RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Context context;
    private String[] imageUrls;

    public ImageListAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item_image, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Picasso.with(context)
                .load(imageUrls[position])
                .networkPolicy(NetworkPolicy.OFFLINE)
                .tag(context)
                .noFade()
                .config(Bitmap.Config.RGB_565)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i("PICASSO", "Cache Success");
                    }

                    @Override
                    public void onError() {
                        Log.e("PICASSO", "Cache failed");
                        // Try again online if cache failed
                        Picasso.with(context)
                                .load(imageUrls[holder.getAdapterPosition()])
                                .into(holder.imageView);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }
}




