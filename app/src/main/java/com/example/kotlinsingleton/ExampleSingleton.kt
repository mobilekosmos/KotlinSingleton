package com.example.kotlinsingleton

import com.example.kotlinsingleton.models.User

object ExampleSingleton {

    val singletonUser: User by lazy {
        User("blablabl@gmail.com", "Superman", "someImage.png")
    }
}