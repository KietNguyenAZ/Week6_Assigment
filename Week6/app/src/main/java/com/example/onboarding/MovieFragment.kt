package com.example.onboarding

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboarding.databinding.FragmentBeginBinding
import com.example.onboarding.databinding.MovieFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.zip.Inflater

class MovieFragment: Fragment() {
    lateinit var binding: MovieFragmentBinding
    lateinit var vm: MovieVM
    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(MovieVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.topRateFragment -> {
                    val controller = findNavController()
                    controller.navigate(R.id.action_movieFragment_to_topRateFragment)
                    true
                }
                else -> {
                    onStart()
                    true
                }
            }
        }
        }

    override fun onStart() {
        super.onStart()
        vm.getNowPlaying()
    }

    private fun setUpMovieList() {
        adapter = MovieAdapter()
        val lm = LinearLayoutManager(context)
        binding.movieRecycleView.layoutManager = lm
        binding.movieRecycleView.adapter = adapter
    }

    private fun registerMovieList() {
        vm.movieData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.res_view_option, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.linear -> {
                adapter = MovieAdapter()
                val lm = LinearLayoutManager(context)
                binding.movieRecycleView.layoutManager = lm
                binding.movieRecycleView.adapter = adapter
                onStart()
                true
            }
            R.id.grid -> {
                adapter = MovieAdapter()
                val lm = GridLayoutManager(context,2)
                binding.movieRecycleView.layoutManager = lm
                binding.movieRecycleView.adapter = adapter
                onStart()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}

