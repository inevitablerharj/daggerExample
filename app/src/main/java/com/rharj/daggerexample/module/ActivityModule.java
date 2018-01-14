package com.rharj.daggerexample.module;

import android.app.Activity;
import android.content.Context;

import com.rharj.daggerexample.interfaces.DaggerExampleApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rharj on 1/14/2018.
 */

@Module
public class ActivityModule {

    Context context;

    public ActivityModule(Activity context){
            this.context = context;
    }

    @Named("activity_context")
    @Provides
    @DaggerExampleApplicationScope
    public Context context(){
        return context;
    }
}
