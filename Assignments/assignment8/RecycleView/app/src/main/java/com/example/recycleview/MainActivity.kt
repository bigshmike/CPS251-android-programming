package com.example.recycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ViewModel with saved state
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this))[MainViewModel::class.java]

        // Setting up the RecyclerView
        layoutManager = LinearLayoutManager(this)
        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(viewModel.getResources())
        binding.contentMain.recyclerView.adapter = adapter
    }

    // this method is needed for device rotation to save data to save state in the ViewModel class
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("titles", ArrayList(viewModel.getTitles()))
        outState.putStringArrayList("details", ArrayList(viewModel.getDetails()))
        outState.putIntegerArrayList("images", ArrayList(viewModel.getImages()))
    }

}