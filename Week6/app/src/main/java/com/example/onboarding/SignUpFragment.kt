package com.example.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.onboarding.databinding.SignUpBinding
import com.example.onboarding.databinding.WelcomeBinding

class SignUpFragment: Fragment() {
    lateinit var binding : SignUpBinding
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.buttonSignUp.setOnClickListener(){
            val fullName = binding.editFullName.text.toString().trim()
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            viewModel.checkSignUpFormat(fullName, email, password)
        }

        binding.txtLogIn.setOnClickListener{
            controller.navigate(R.id.action_signUpFragment_to_loginFragment)
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
                val fullName = binding.editFullName.text.toString().trim()
                val email = binding.editTextTextEmailAddress.text.toString().trim()
                val password = binding.editTextTextPassword.text.toString().trim()
                DataStore.userData.add(mutableMapOf("fullname" to fullName,
                    "email" to email, "password" to password))
                Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

}