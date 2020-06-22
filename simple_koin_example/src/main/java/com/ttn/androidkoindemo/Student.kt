package com.ttn.androidkoindemo

import androidx.lifecycle.ViewModel


/*
 * Created by Naveen Verma on 22/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

/**
 * Demo class
 */
class Student(private val course: SchoolCourse, private val friend: Friend) {

/*
    private val course: SchoolCourse = SchoolCourse()
    private val friend: Friend = Friend()*/

    fun beSmart() {
        course.study()
        friend.hangout()
    }
}

/**
 * First dependency
 */
class SchoolCourse {
    fun study() {
        println("I'm studying")
    }
}

/**
 * Second dependency
 */
class Friend {
    fun hangout() {
        println("We're hanging out")
    }
}

/**
 * ViewModel class
 */
class MainViewModel(val course: SchoolCourse, val friend: Friend) : ViewModel() {
    fun performAction() {
        println("We're in viewModel")
    }
}

