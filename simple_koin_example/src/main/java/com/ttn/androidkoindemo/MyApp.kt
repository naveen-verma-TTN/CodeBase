package com.ttn.androidkoindemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

/**
 * Application class
 */
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // register the koin modules
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, viewModelModule))
        }
    }
}