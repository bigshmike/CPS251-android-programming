package com.example.navigationproject.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationproject.R
import com.example.navigationproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel

        /*binding.button1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.mainToSecond)
        }*/

        binding.button1.setOnClickListener {
            val image = R.drawable.android_image_1 // <- gets image from Resources
            val imageTitle = "Image 1" // <- placeholder text title for use on Fragment 2
            val action = MainFragmentDirections.mainToSecond(image, imageTitle) // <- args passed are image and imageTitle
            Navigation.findNavController(it).navigate(action) // <- Navigation class then performs the action defined on line 36
        }

        binding.button2.setOnClickListener {
            val image = R.drawable.android_image_2
            val imageTitle = "Image 2"
            val action = MainFragmentDirections.mainToSecond(image, imageTitle)
            Navigation.findNavController(it).navigate(action)
        }

        binding.button3.setOnClickListener {
            val image = R.drawable.android_image_3
            val imageTitle = "Image 3"
            val action = MainFragmentDirections.mainToSecond(image, imageTitle)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}