package com.rharj.daggerexample.activity;

import android.app.Service;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.rharj.daggerexample.MainActvityFeature.DaggerMainActivityComponent;
import com.rharj.daggerexample.MainActvityFeature.MainActivityComponent;
import com.rharj.daggerexample.MainActvityFeature.MainActivityModule;
import com.rharj.daggerexample.R;
import com.rharj.daggerexample.adapter.DaggerExampleAdapter;
import com.rharj.daggerexample.application.DaggerExampleApplication;
import com.rharj.daggerexample.component.DaggerDaggerExampleComponent;
import com.rharj.daggerexample.component.DaggerExampleComponent;
import com.rharj.daggerexample.interfaces.ServiceApi;
import com.rharj.daggerexample.model.DaggerExampleModel;
import com.rharj.daggerexample.module.ContextModule;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recycler_list;

    Picasso picasso;

    @Inject
    ServiceApi serviceApi;

    @Inject
    DaggerExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        beforeDagger2();
        afterDagger();
        afterActivityLevelComponent();
        populateUsers();
    }


    private void initViews() {
        recycler_list = findViewById(R.id.recycler_list);
        recycler_list.setLayoutManager(new LinearLayoutManager(this));
    }

    private void afterActivityLevelComponent() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .daggerExampleComponent(DaggerExampleApplication.get(this).getDaggerExampleComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

    public void afterDagger() {
        DaggerExampleComponent daggerRandomUserComponent = DaggerDaggerExampleComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        picasso = daggerRandomUserComponent.getPicasso();
        serviceApi = daggerRandomUserComponent.getServiceApi();
    }

    private void beforeDagger2() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Timber.plant(new Timber.DebugTree());

        File cacheFile = new File(this.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); //10 MB

        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);

        picasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void populateUsers() {
        Call<DaggerExampleModel> randomUsersCall = getService().getAllDogBreeds();
        randomUsersCall.enqueue(new Callback<DaggerExampleModel>() {
            @Override
            public void onResponse(Call<DaggerExampleModel> call, @NonNull Response<DaggerExampleModel> response) {
                if(response.isSuccessful()) {
                    mAdapter.setItems(response.body().getMessage());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<DaggerExampleModel> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    public ServiceApi getService(){
        return serviceApi;
    }
}
