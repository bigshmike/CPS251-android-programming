package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycleview.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras ?: return
        val myTitle = extras.getString("title")
        val myDetails = extras.getString("details")
        val myImage = extras.getInt("image")
        binding.titleTextView.text = myTitle
        binding.detailsTextView.text = myDetails
        binding.imageView.setImageResource(myImage)
    }
}