package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDAO {

    @Insert
    fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE contactNumber = :number")
    fun findContactByNumber(number: String): List<Contact>

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    fun findContactByName(name: String): List<Contact>

    @Query("DELETE FROM contacts WHERE contactNumber = :number")
    fun deleteContact(number: String)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    fun sortContactsAsc(): List<Contact>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    fun sortContactsDesc(): List<Contact>

}