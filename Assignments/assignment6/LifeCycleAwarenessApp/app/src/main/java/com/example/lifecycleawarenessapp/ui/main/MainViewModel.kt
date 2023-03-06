package com.example.lifecycleawarenessapp.ui.main

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    private var output: String = ""
    private val logTime = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault()) // displays time with Hour, min, sec, and 3 milliseconds after decimal

    // initialize output and assign it to SaveState - using variable "output" - will return blank string if one has not been initialized already
    // concatenate the list of strings into a single string with new line separator between each list element
    init {
        output = state.get<List<String>>("output")?.joinToString("\n") ?: ""
    }

    // getter for instance variable output
    // used in MainFragment to get the text for the TextView
    fun getOutput(): String {
        return output
    }

    // converts system time in milliseconds and formats it like I setup in the instance variable logTime
    // used in DemoObserver
    fun getLogTime(): String {
        val timeInMilliseconds = System.currentTimeMillis()
        return logTime.format(timeInMilliseconds)
    }

    // called whenever the output is modified and it saves the data to the Save State
    private fun saveState() {
        state["output"] = output
    }

    // getter for use in DemoObserver
    // whenever the appropriate function gets called, it will pass a message and concatenate with current output String and add a line separator at the end
    fun getMessage(message: String) {
        output += "$message\n"
        saveState()
    }

    // called when ViewModel is about to be destroyed and saves data to the save state
    override fun onCleared() {
        super.onCleared()
        saveState()
    }
}

