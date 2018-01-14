package com.rharj.daggerexample.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.rharj.daggerexample.interfaces.ApplicationContext;
import com.rharj.daggerexample.interfaces.DaggerExampleApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by rharj on 1/14/2018.
 */

@Module(includes = OkHttpClientModule.class)
public class PicassoModule {

    @Provides
    @DaggerExampleApplicationScope
    public Picasso picasso(@ApplicationContext Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context).
                downloader(okHttp3Downloader).
                build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }
}
