package com.example.onboarding

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.onboarding.databinding.ProfileScreenBinding
import com.example.onboarding.databinding.SignUpBinding

class ProfileFragment: Fragment() {
    lateinit var binding : ProfileScreenBinding
    private lateinit var editInformation: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ProfileScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        binding.fullNameProfile.text = DataStore.currentUser["fullname"]
        binding.emailProfile.text = DataStore.currentUser["email"]
        binding.textView24.text = DataStore.currentUser["full name"]
        binding.phoneProfile.text = DataStore.currentUser["phone"]

        showDialogEditText()

        binding.SignOutToWelcome.setOnClickListener{
            controller.navigate(R.id.action_profileFragment_to_welcomeFragment)
        }
    }


    private fun showDialogEditText() {
        val profileFUllName = binding.fullNameProfile
        val profileEmail = binding.emailProfile
        val profilePhone = binding.phoneProfile


        profileFUllName.setOnClickListener() {
            /*val builder = AlertDialog.Builder(this)*/
            val builder = AlertDialog.Builder(binding.root.context)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)
            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmName()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

        profileEmail.setOnClickListener() {
            val builder = AlertDialog.Builder(binding.root.context)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)


            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)


            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmEmail()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }

        }

        profilePhone.setOnClickListener() {
            val builder = AlertDialog.Builder(binding.root.context)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.custom_dialog, null)
            editInformation = dialogLayout.findViewById<EditText>(R.id.changeInforation)
            with(builder) {

                setPositiveButton(R.string.confirm) { dialog, which ->
                    confirmPhone()
                }
                setNegativeButton(R.string.cancel) { dialog, which ->
                    Log.d("Main", "Negative Button Clicked")
                }
                setView(dialogLayout)
                show()
            }
        }


    }

    private fun confirmName() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(binding.root.context, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.fullNameProfile.text = editInformation.text
            binding.textView24.text = editInformation.text.toString()

        }
    }

    private fun confirmEmail() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(binding.root.context, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.emailProfile.text = editInformation.text
        }
    }

    private fun confirmPhone() {
        if (editInformation.text.isNullOrBlank()) {
            Toast.makeText(binding.root.context, "Blank", Toast.LENGTH_SHORT).show()
        } else {
            binding.phoneProfile.text = editInformation.text
        }
    }
}