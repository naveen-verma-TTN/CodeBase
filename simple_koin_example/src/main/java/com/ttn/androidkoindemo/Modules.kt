package com.ttn.androidkoindemo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

val appModule = module {

    // Defines a singleton of SchoolCourse
    single { SchoolCourse() }

    // Defines a factory(create new instance evert time)
    factory { Friend() }

    factory { Student(get(), get()) }
}

// inject the viewModel dependencies
val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}