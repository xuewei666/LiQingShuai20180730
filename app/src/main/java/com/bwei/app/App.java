package com.bwei.app;

import android.app.Application;

import com.bwei.imageloaderlibrary.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration configuration = ImageLoaderUtils.getConfiguration(this);
        ImageLoader.getInstance().init(configuration);
    }
}
