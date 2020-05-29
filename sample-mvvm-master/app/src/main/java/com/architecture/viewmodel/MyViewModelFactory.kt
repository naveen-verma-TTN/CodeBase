package com.architecture.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.architecture.MyApp
import com.architecture.model.repo.Repository
import com.architecture.model.repo.RepositoryImpl

class MyViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SigninViewModel::class.java) -> {
                SigninViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(MyApp.mInstance.applicationContext, RepositoryImpl as Repository) as T
            }
            else -> throw IllegalArgumentException("Unknown viewmodel class")
        }
    }
}