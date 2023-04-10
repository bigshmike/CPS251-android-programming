package com.example.coroutines

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private var state: SavedStateHandle) : ViewModel() {

    private var names = state.get<ArrayList<String>>("names") ?: ArrayList()

    init {
        // Initialize resources with saved state
        if (names.isNotEmpty()) { // if not empty, there is a save state and setters are called appropriately
            setNames(names)
        }
        // If there is no save state, one wil be Initialized here
        else {
            state = SavedStateHandle()
        }
    }

    // add name to List and updated the save state
    fun addName(name: String) {
        names.add(name)
        saveState()
    }

    // Getters & Setters
    fun getNames(): List<String> {
        return names
    }

    fun setNames(names: ArrayList<String>) {
        this.names = names
    }

    private fun saveState() {
        state["names"] = names
    }

    override fun onCleared() {
        super.onCleared()
        saveState()
    }

}