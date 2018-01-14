package com.rharj.daggerexample.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rharj on 1/14/2018.
 */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface DaggerExampleApplicationScope {
}
