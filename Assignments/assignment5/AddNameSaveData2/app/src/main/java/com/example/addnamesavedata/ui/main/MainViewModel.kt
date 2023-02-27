package com.example.addnamesavedata.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    val listOfNames = MutableLiveData<List<String>>(state["listOfNames"] ?: emptyList())

    // updates the listOfNames variable with the new name and saves the updated state
    fun addName(name: String) {
        val names = listOfNames.value.orEmpty().toMutableList() // holds current list of names
        names.add(name) // Add the new name to the list of names
        listOfNames.value = names // Update LiveData with the new list of names
        saveState()
    }

    // saves the current list of names to the Save State
    private fun saveState() {
        state["listOfNames"] = listOfNames.value
    }

    // called when the ViewModel is about to be destroyed
    override fun onCleared() {
        super.onCleared()
        saveState()
    }
}

