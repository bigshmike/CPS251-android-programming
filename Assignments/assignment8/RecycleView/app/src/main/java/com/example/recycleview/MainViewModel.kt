package com.example.recycleview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val state: SavedStateHandle) : ViewModel() {

    private var resources: AppResources = AppResources()

    private var titles = state.get<ArrayList<String>>("titles") ?: ArrayList()
    private var details = state.get<ArrayList<String>>("details") ?: ArrayList()
    private var images = state.get<ArrayList<Int>>("images") ?: ArrayList()


    init {
        // Initialize resources with saved state or new instance of AppResources
        if (titles.isNotEmpty() && details.isNotEmpty() && images.isNotEmpty()) { // if not empty, there is a save state and setters are called appropriately
            resources.setTitles(titles.toTypedArray())
            resources.setDetails(details.toTypedArray())
            resources.setImages(images.toIntArray())
        }
        // If there is no save state, one wil be Initialized here with an instance of AppResources
        else {
            resources = AppResources()
        }
    }

    // Getters & Setters
    fun getResources(): AppResources {
        return resources
    }

    fun getTitles(): List<String> {
        return titles
    }

    fun getDetails(): List<String> {
        return details
    }

    fun getImages(): List<Int> {
        return images
    }

    private fun saveState() {
        state["titles"] = titles
        state["details"] = details
        state["images"] = images
    }

    override fun onCleared() {
        super.onCleared()
        saveState()
    }

}


