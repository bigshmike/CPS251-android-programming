package com.example.roomdemo

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @ColumnInfo(name = "contactName")
    var contactName: String = ""

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "contactNumber")
    var contactNumber: String = ""


    constructor() {}

    constructor(contactName: String, contactNumber: String) {
        this.contactName = contactName
        this.contactNumber = contactNumber
    }

    override fun toString(): String {
        return "Contact(contactName='$contactName', contactNumber='$contactNumber')"
    }
}