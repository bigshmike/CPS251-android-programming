package com.example.addnamesavedata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.addnamesavedata.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(requireActivity().application, this)
        )[MainViewModel::class.java]

        // If there is saved instance state, restore the list of names
        if (savedInstanceState != null) {
            val names = savedInstanceState.getStringArrayList("listOfNames")
            names?.let { viewModel.setListOfNames(it) }
        }

        // Checking for enter name field to be empty or not; if no text, should say "enter name" as an auto-fill hint
        if (binding.enterNameTextField.text.isEmpty()) {
            binding.enterNameTextField.hint = "Enter Name"
        }

        // checks if the list is empty; if it is empty, should say "No names added yet" else, it will call the viewModel.getListOfNames() method
        // and reset the hint to a blank string
        if (viewModel.getListOfNames().isEmpty()) {
            binding.listOfNamesTextView.hint = "No names added yet"
        }
        else {
            binding.listOfNamesTextView.text = viewModel.getListOfNames().joinToString("\n")
            binding.listOfNamesTextView.hint = ""
        }

        binding.addNameButton.setOnClickListener {
            // Get the name from the text field
            val name = binding.enterNameTextField.text.toString()

            // name gets added to list
            if (name.isNotEmpty()) {
                viewModel.addName(name)

                // text view updated to include new name; appends "\n" with joinToString() method
                binding.listOfNamesTextView.text = viewModel.getListOfNames().joinToString("\n")

                // reset/clear textField
                binding.enterNameTextField.text.clear()
            }
        }
    }

    // Save the list of names in the view model to the saved instance
    // otherwise data would get lost on screen rotate
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("listOfNames", ArrayList(viewModel.getListOfNames()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}