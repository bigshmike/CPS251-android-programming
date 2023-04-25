package com.example.roomdemo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdemo.Contact
import com.example.roomdemo.ContactRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: LiveData<List<Contact>>?
    private val searchResults: MutableLiveData<List<Contact>>

    init {
        allContacts = repository.allContacts
        searchResults = repository.searchResults
    }

    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }

    fun findContactByNumber(number: String) {
        repository.findContactByNumber(number)
    }

    fun findContactByName(name: String) {
        repository.findContactByName(name)
    }

    fun deleteContact(number: String) {
        repository.deleteContact(number)
    }

    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults
    }

    fun getAllContacts(): LiveData<List<Contact>>? {
        return allContacts
    }

    fun getContactsSortedByNameAsc() {
        return repository.getContactsSortedByNameAsc()
    }

    fun getContactsSortedByNameDesc() {
        return repository.getContactsSortedByNameDesc()
    }

    fun getContacts() {
        return repository.getContacts()
    }

}