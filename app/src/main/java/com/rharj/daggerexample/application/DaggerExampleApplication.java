package com.rharj.daggerexample.application;

import android.app.Activity;
import android.app.Application;

import com.rharj.daggerexample.component.DaggerDaggerExampleComponent;
import com.rharj.daggerexample.component.DaggerExampleComponent;
import com.rharj.daggerexample.module.ContextModule;

import timber.log.Timber;

/**
 * Created by rharj on 1/14/2018.
 */

public class DaggerExampleApplication extends Application {

    private DaggerExampleComponent daggerExampleComponent;

    public static DaggerExampleApplication get(Activity activity){
        return (DaggerExampleApplication) activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        daggerExampleComponent = DaggerDaggerExampleComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public DaggerExampleComponent getDaggerExampleComponent(){
        return daggerExampleComponent;
    }
}
