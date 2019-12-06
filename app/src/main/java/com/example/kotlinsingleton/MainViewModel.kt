package com.example.kotlinsingleton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kotlinsingleton.models.User
import com.example.kotlinsingleton.repository.MainRepository

class MainViewModel : ViewModel() {
    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations
        .switchMap(_userId) {
            MainRepository.getUser(it)
        }

    fun setUserId(userId: String) {
        if (_userId.value == userId) {
            return
        }
        _userId.value = userId
    }

    fun cancelJobs() {
        MainRepository.cancelJobs()
    }
}