package com.example.addnamesavedata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.addnamesavedata.BR.mainViewModel
import com.example.addnamesavedata.R
import com.example.addnamesavedata.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.setVariable(mainViewModel, viewModel)
        // observing live data in the MainViewModel to update the view layer when the list gets updated/has a name added to it
        // once a change has been made to the list, the input text field will get cleared
        viewModel.listOfNames.observe(viewLifecycleOwner) {
            binding.enterNameTextField.text.clear() // clear the input field
        }
    }

}