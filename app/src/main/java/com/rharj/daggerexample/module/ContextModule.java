package com.rharj.daggerexample.module;

import android.content.Context;

import com.rharj.daggerexample.interfaces.ApplicationContext;
import com.rharj.daggerexample.interfaces.DaggerExampleApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rharj on 1/14/2018.
 */

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationContext
    @DaggerExampleApplicationScope
    public Context context(){
        return context.getApplicationContext();
    }


}
