package com.example.roomdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.Contact
import com.example.roomdemo.R
import com.example.roomdemo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var adapter: ContactListAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        listenerSetup()
        observerSetup()
        recyclerSetup()

        return binding.root
    }

    private fun listenerSetup() {

        binding.addButton.setOnClickListener {
            val name = binding.contactName.text.toString()
            val phone = binding.contactNumber.text.toString()

            // If both fields are not empty, create new Contact object, insert it into database, and clear input fields
            if (name != "" && phone != "") {
                val contact = Contact(name, phone)
                viewModel.insertContact(contact)
                clearFields()
            }
            else {
                Toast.makeText(
                    activity, "A contact must have a name and a phone number to be stored",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.findButton.setOnClickListener {
            val number = binding.contactNumber.text.toString()
            val name = binding.contactName.text.toString()

            // If number field is not empty, find contacts by number
            if (number.isNotEmpty()) {
                viewModel.findContactByNumber(number)
            }

            // If name field is not empty, find contacts by name
            if (name.isNotEmpty()) {
                viewModel.findContactByName(name)
            }

            // If both fields are empty, get all contacts from database
            else {
                //viewModel.getAllContacts()
                viewModel.getContacts()
                Toast.makeText(
                    activity, "Please enter a name or phone number to search",
                    Toast.LENGTH_LONG).show()
            }
            clearFields()
        }

        binding.ascendButton.setOnClickListener {
            viewModel.getContactsSortedByNameAsc()
        }

        binding.descButton.setOnClickListener {
            viewModel.getContactsSortedByNameDesc()
        }


    }

    private fun observerSetup() {

        viewModel.getAllContacts()?.observe(viewLifecycleOwner) { contacts ->
            contacts?.let {
                adapter?.setContactList(it)
            }
        }

        viewModel.getSearchResults().observe(viewLifecycleOwner) { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    adapter?.setSearchResults(contacts)
                }
                else {
                    Toast.makeText(
                        activity, "Could not find any contacts by that name or number",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.card_layout)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter

        adapter!!.setOnDeleteClickListener(object : ContactListAdapter.OnDeleteClickListener {
            override fun onDeleteClick(contact: Contact) {
                viewModel.deleteContact(contact.contactNumber)
            }
        })
    }

    private fun clearFields() {
        binding.contactName.setText("")
        binding.contactNumber.setText("")
    }
}