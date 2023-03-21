package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onboarding.databinding.LogInBinding
import com.example.onboarding.databinding.WelcomeBinding

class LoginFragment: Fragment() {
    lateinit var binding : LogInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = LogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        binding.LoginToFrofile.setOnClickListener(){
            clickLogIn()
        }


        listenerSuccessEvent()
        listenerErrorEvent()
    }

    private fun listenerErrorEvent(){
        viewModel.isErrorEvent.observe(viewLifecycleOwner){ errMsg ->
            /*Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()*/
            Toast.makeText(binding.root.context, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenerSuccessEvent(){
        viewModel.isSuccessEvent.observe(viewLifecycleOwner) {isSuccess ->
            if(isSuccess){
                val controller = findNavController()
                controller.navigate(R.id.action_loginFragment_to_movieFragment)
            }
        }
    }
    private fun clickLogIn(){
        val email = binding.editTextTextEmailAddressForLogIn.text.toString().trim()
        val password = binding.editTextTextPasswordLogIn.text.toString().trim()
        viewModel.checkOnClick(email,password)
    }
}