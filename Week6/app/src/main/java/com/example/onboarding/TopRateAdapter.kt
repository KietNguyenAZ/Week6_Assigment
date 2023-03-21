package com.example.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onboarding.databinding.MovieItemBinding

class TopRateAdapter : ListAdapter<Movie, TopRateAdapter.TopRateVH>(MovieDiffUtilCallback()) {

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    class TopRateVH private constructor(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): TopRateAdapter.TopRateVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
                return TopRateVH(binding)
            }
        }

        fun binding(item: Movie) {
            binding.movieTitle.text = item.title?.trim()
            binding.movieDes.text = item.overview?.trim()
            val urlImage = "https://image.tmdb.org/t/p/w500${item.posterPath}"
            Glide.with(itemView.context).load(urlImage)
                .into(binding.imageMovieView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRateVH {
        return TopRateVH.from(parent)
    }

    override fun onBindViewHolder(holder: TopRateVH, position: Int) {
        val movie = getItem(position)
        holder.binding(movie)
    }
}