package com.example.wcastiblanco.picassotest;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by wilsoncastiblanco on 10/17/16.
 */
public class ImageLoaderApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
