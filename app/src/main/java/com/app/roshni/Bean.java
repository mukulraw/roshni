package com.app.roshni;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Bean extends Application {
    private static Context context;

    public String baseurl = "http://mrtecks.com/";


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

    }
}
