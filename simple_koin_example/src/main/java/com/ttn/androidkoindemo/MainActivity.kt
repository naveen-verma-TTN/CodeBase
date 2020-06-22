package com.ttn.androidkoindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainViewModel>()  // option 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()
    }

    /**
     * fun to inject dependencies
     */
    private fun injectDependencies() {
        // get<Reference>() will automatically inject the dependencies
        val student = get<Student>()
        student.beSmart()

        val student1 = get<Student>()
        student1.beSmart()

        val vm = getViewModel<MainViewModel>() // option 1
        vm.performAction()

        doSomething()
    }

    private fun doSomething() {
        viewModel.performAction()
    }
}