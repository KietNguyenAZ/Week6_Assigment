package com.example.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboarding.databinding.ActivityMainBinding
import com.example.onboarding.databinding.Onboarding3Binding

class Onboarding3Fragment: Fragment() {
    lateinit var binding : Onboarding3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Onboarding3Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moveScreen3.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_onboarding3Fragment_to_welcomeFragment)
        }
    }
}