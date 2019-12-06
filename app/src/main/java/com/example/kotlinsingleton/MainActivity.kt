package com.example.kotlinsingleton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        println("DEBUG: ${ExampleSingleton.singletonUser.hashCode()}")

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.user.observe(this, Observer {
            println("DEBUG: ${it}")
        })
        mViewModel.setUserId("1")
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.cancelJobs()
    }
}
