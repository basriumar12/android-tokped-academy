package app.tokopedia.movie.ui

import androidx.lifecycle.*
import app.tokopedia.movie.domain.PopularMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movies


interface  PopularMovieContract{
    fun getPopularMovie()
}
class PopularMovieVieModel (
    private val useCase: PopularMovieUseCase
) : ViewModel(), PopularMovieContract{
    //objek live data
    private val _movies = MutableLiveData<Movies>()
    val movie : LiveData<Movies>
        get() = _movies

    val _error = MutableLiveData<String>()
    val error : LiveData<String>
    get() =  _error

    init {
      getPopularMovie()
    }
    override  fun getPopularMovie() {
        viewModelScope.launch(Dispatchers.IO){
            val response = useCase.get()
            withContext(Dispatchers.Main){
                when (response) {
                    is ResultState.Success -> {
                        _movies.value = response.data
                    }
                    is ResultState.Error -> {
                        _error.value = response.error
                    }
                }
            }
        }



    }
}