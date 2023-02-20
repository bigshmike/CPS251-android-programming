package com.example.addnamesavedata.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    // will return an empty list if one has not already been initialized
    private var listOfNames = state.get<ArrayList<String>>("listOfNames") ?: arrayListOf()

    // add name to List and updated the save state
    fun addName(name: String) {
        listOfNames.add(name)
        saveState()
    }

    // getter for listOfNames
    fun getListOfNames(): List<String> {
        return listOfNames
    }

    // setter for listOfNames
    fun setListOfNames(names: ArrayList<String>) {
        listOfNames = names
    }

    // saves the current list of names to the SavedState
    private fun saveState() {
        state["listOfNames"] = listOfNames
    }

    // called when the ViewModel is about to be destroyed; it saves the current list of names to the SavedState.
    override fun onCleared() {
        super.onCleared()
        saveState()
    }
}
