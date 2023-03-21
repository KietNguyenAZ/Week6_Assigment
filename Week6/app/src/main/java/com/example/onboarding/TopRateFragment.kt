package com.example.onboarding

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboarding.databinding.MovieFragmentBinding
import com.example.onboarding.databinding.TopRateFragmentBinding

class TopRateFragment: Fragment() {
    lateinit var binding: TopRateFragmentBinding
    lateinit var vm: TopRateVM
    lateinit var adapter: MovieAdapter
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TopRateFragmentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(TopRateVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.movieFragment -> {
                    val controller = findNavController()
                    controller.navigate(R.id.action_topRateFragment_to_movieFragment2)
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
        vm.getTopRates()
    }

    private fun setUpMovieList() {
        adapter = MovieAdapter()
        val lm = LinearLayoutManager(context)
        binding.topRateRecycleView.layoutManager = lm
        binding.topRateRecycleView.adapter = adapter
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
                binding.topRateRecycleView.layoutManager = lm
                binding.topRateRecycleView.adapter = adapter
                onStart()
                true
            }
            R.id.grid -> {
                adapter = MovieAdapter()
                val lm = GridLayoutManager(context,2)
                binding.topRateRecycleView.layoutManager = lm
                binding.topRateRecycleView.adapter = adapter
                onStart()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}