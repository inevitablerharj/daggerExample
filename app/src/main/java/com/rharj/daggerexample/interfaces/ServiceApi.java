package com.rharj.daggerexample.interfaces;

import com.rharj.daggerexample.model.DaggerExampleModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rharj on 1/14/2018.
 */

public interface ServiceApi {

    @GET("api/breeds/list/all")
    Call<DaggerExampleModel> getAllDogBreeds();
}
