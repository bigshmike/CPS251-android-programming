package com.example.roomdemo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository(application: Application) {

    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDAO: ContactDAO?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?

    init {
        val db: ProductRoomDatabase? = ProductRoomDatabase.getDatabase(application)
        contactDAO = db?.contactDAO()
        allContacts = contactDAO?.getAllContacts()
    }

    fun insertContact(newContact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newContact)
        }
    }

    private suspend fun asyncInsert(contact: Contact) {
        contactDAO?.insertContact(contact)
    }

    fun deleteContact(number: String) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(number)
        }
    }

    private suspend fun  asyncDelete(number: String) {
        contactDAO?.deleteContact(number)
    }

    fun findContactByNumber(number: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFindByNumber(number).await()
        }
    }

    private suspend fun asyncFindByNumber(number: String): Deferred<List<Contact>?> =

        coroutineScope.async(Dispatchers.IO) {
            return@async contactDAO?.findContactByNumber(number)
        }

    fun findContactByName(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFindByName(name).await()
        }
    }

    private suspend fun asyncFindByName(name: String): Deferred<List<Contact>?> =

        coroutineScope.async(Dispatchers.IO) {
            return@async contactDAO?.findContactByName(name)
        }

    fun getContactsSortedByNameAsc() {
        coroutineScope.async(Dispatchers.Main) {
            searchResults.value = asyncSortAsc().await()
        }
    }

    private suspend fun asyncSortAsc(): Deferred<List<Contact>?> =

        coroutineScope.async(Dispatchers.IO) {
            return@async contactDAO?.sortContactsAsc()
        }

    fun getContactsSortedByNameDesc() {
        coroutineScope.async(Dispatchers.Main) {
            searchResults.value = asyncSortDesc().await()
        }
    }

    private suspend fun asyncSortDesc(): Deferred<List<Contact>?> =

        coroutineScope.async(Dispatchers.IO) {
            return@async contactDAO?.sortContactsDesc()
        }
}