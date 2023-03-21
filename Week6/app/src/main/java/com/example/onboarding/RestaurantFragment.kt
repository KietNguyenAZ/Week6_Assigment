package com.example.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboarding.databinding.ActivityMainBinding
import com.example.onboarding.databinding.RestaurantMainBinding

class RestaurantFragment: Fragment() {
    lateinit var binding : RestaurantMainBinding
    lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = RestaurantMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        setUpRecycleView()
        binding.moveToProfileScreeen.setOnClickListener{
            controller.navigate(R.id.action_restaurantFragment_to_profileFragment)
        }
    }

    private fun setUpRecycleView(){
        binding.recyclerViewRestaurant.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = RestaurantAdapter(RestaurantDataStore.getDataSet())
        binding.recyclerViewRestaurant.adapter = adapter

    }



}