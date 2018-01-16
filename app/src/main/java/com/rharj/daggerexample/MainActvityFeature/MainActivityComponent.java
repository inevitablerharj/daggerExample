package com.rharj.daggerexample.MainActvityFeature;

import com.rharj.daggerexample.activity.MainActivity;
import com.rharj.daggerexample.component.DaggerExampleComponent;

import dagger.Component;

/**
 * Created by rharj on 1/16/2018.
 */
@Component(modules = MainActivityModule.class, dependencies = DaggerExampleComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);
}
