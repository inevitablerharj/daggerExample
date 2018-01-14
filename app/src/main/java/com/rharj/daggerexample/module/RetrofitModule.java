package com.rharj.daggerexample.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rharj.daggerexample.interfaces.DaggerExampleApplicationScope;
import com.rharj.daggerexample.interfaces.ServiceApi;
import com.rharj.daggerexample.utils.AppConstant;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rharj on 1/14/2018.
 */
@Module(includes = OkHttpClientModule.class)
public class RetrofitModule {

    @Provides
    public ServiceApi getServiceApi(Retrofit retrofit){
        return retrofit.create(ServiceApi.class);
    }

    @Provides
    @DaggerExampleApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}
