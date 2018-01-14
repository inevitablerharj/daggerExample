package com.rharj.daggerexample.component;

import com.rharj.daggerexample.interfaces.DaggerExampleApplicationScope;
import com.rharj.daggerexample.interfaces.ServiceApi;
import com.rharj.daggerexample.module.PicassoModule;
import com.rharj.daggerexample.module.RetrofitModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by rharj on 1/14/2018.
 */
@DaggerExampleApplicationScope
@Component(modules={RetrofitModule.class, PicassoModule.class})
public interface DaggerExampleComponent {

    ServiceApi getServiceApi();
    Picasso getPicasso();
}
