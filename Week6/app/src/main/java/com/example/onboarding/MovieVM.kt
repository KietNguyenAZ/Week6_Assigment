package com.example.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieVM: ViewModel() {
    private var _movieData: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val movieData: LiveData<List<Movie>>
        get() = _movieData

    fun getNowPlaying() {
        viewModelScope.launch {
            val movieResp = MovieRestClient.getInstance().api.listNowPlayMovies(
                language = "en-US",
                page = 1,)
            _movieData.postValue(movieResp.results!!)
        }
    }
}