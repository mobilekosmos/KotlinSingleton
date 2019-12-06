package com.example.kotlinsingleton.repository

import androidx.lifecycle.LiveData
import com.example.kotlinsingleton.api.MyRetrofitBuilder
import com.example.kotlinsingleton.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {
    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {
        job = Job()
        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { jobLambda ->
                    CoroutineScope(IO + jobLambda).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            jobLambda.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs() {
        job?.cancel()
    }
}