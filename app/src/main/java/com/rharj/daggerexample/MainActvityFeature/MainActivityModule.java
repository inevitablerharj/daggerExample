package com.rharj.daggerexample.MainActvityFeature;

import com.rharj.daggerexample.activity.MainActivity;
import com.rharj.daggerexample.adapter.DaggerExampleAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rharj on 1/16/2018.
 */
@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public DaggerExampleAdapter daggerExampleAdapter(Picasso picasso){
        return new DaggerExampleAdapter(mainActivity, picasso);
    }
}
