package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var viewModel: MainViewModel

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private suspend fun performTask(name: String): String {
        val delayTime = (1..10).random() * 1000L
        delay(delayTime)
        return "The name is $name and the delay was $delayTime milliseconds"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the ViewModel with saved state
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this))[MainViewModel::class.java]

        // Setting up the RecyclerView
        layoutManager = LinearLayoutManager(this)
        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(viewModel)
        binding.contentMain.recyclerView.adapter = adapter

    }

    fun launchCoroutines(view: View) {
        val names = binding.nameTextField.text.toString().split("\n")
        binding.nameTextField.text.clear()
        coroutineScope.launch(Dispatchers.Main) {
            for (name in names) {
                    val result = performTask(name)
                    viewModel.addName(result)
                    adapter?.notifyItemInserted(viewModel.getNames().size - 1)
            }
        }
    }

    // this method is needed for device rotation to save data to save state in the ViewModel class
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("names", ArrayList(viewModel.getNames()))
    }

}