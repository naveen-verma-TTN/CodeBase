package com.ttn.dagger2.di.main;

/*
 * Created by Naveen Verma on 12/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Documented
@Retention(RUNTIME)
public @interface MainScope {}
