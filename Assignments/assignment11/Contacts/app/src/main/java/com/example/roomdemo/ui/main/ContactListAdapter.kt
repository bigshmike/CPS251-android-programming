package com.example.roomdemo.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.Contact
import com.example.roomdemo.R

class ContactListAdapter(private val layout: Int) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactList: List<Contact>? = null
    private var searchResults: List<Contact> = emptyList() // search results ; default value is an empty list
    private var onDeleteClickListener: OnDeleteClickListener? = null // hold the onDeleteClickListener object that will be set from the MainActivity.

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val name = holder.name
        val number = holder.number
        val currentContact = contactList!![position]  // Get the current contact from the contactList.

        // Set the onClickListener for the delete icon to call the onDeleteClick method of the onDeleteClickListener
        holder.icon.setOnClickListener {
            onDeleteClickListener?.onDeleteClick(currentContact)
        }

        // Determine which contact to display based on whether there are search results or not...
        val contact = if (searchResults.isNotEmpty() && searchResults.size > position) {
            searchResults[position]
        }
        else {
            contactList!![position]
        }

        // Set textViews to the contact's name and phone number.
        name.text = contact.contactName
        number.text = contact.contactNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    fun setSearchResults(results: List<Contact>) {
        searchResults = results
        notifyDataSetChanged()
    }

    // Return the size of the list of contacts to display
    override fun getItemCount(): Int {
        return if (searchResults.isNotEmpty()) searchResults.size else contactList?.size ?: 0
    }

    // Define the ViewHolder class that holds references to the card layout
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.contactsName)
        var number: TextView = itemView.findViewById(R.id.contactsPhone)
        var icon: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    // Define the OnDeleteClickListener interface that will be implemented in the MainActivity
    interface OnDeleteClickListener {
        fun onDeleteClick(contact: Contact)
    }

    fun setOnDeleteClickListener(listener: OnDeleteClickListener) {
        onDeleteClickListener = listener
    }
}
