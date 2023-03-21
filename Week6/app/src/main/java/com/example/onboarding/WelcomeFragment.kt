package com.example.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboarding.databinding.Onboarding3Binding
import com.example.onboarding.databinding.WelcomeBinding

class WelcomeFragment: Fragment() {
    lateinit var binding : WelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = WelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        binding.txtSignIn.setOnClickListener {
            controller.navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        binding.startwithEorP.setOnClickListener{
            controller.navigate(R.id.action_welcomeFragment_to_signUpFragment)
        }
    }
}