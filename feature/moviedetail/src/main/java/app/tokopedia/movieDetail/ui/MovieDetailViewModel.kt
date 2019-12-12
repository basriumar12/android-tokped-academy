package app.tokopedia.movieDetail.ui

import androidx.lifecycle.*
import app.tokopedia.movieDetail.domain.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.Movies


interface MovieDetailContract {
    fun getMovieDetail(movieId : String)
    fun setMovieId(movieId : String)
}
class MovieDetailViewModel
    (private val useCase : MovieDetailUseCase ) : ViewModel(), MovieDetailContract{


    private val _movieId = MutableLiveData<String>()

    private val _movie = MediatorLiveData<Movie>()
    val movie : LiveData<Movie>
        get() = _movie

    val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() =  _error

    val _showProgress = MutableLiveData<Boolean>()
    val showProgress : LiveData<Boolean>
    get() = _showProgress


    init {
        _movie.addSource(_movieId){
            getMovieDetail(it)
        }
    }
    override fun setMovieId(movieId: String) {

        _movieId.value = movieId
    }

    override fun getMovieDetail(movieId : String) {

        viewModelScope.launch(Dispatchers.IO) {
//            _showProgress.value = true
            val result = useCase.get(movieId)
            withContext(Dispatchers.Main){
                when(result){
                    is ResultState.Success ->{
                        _movie.value = result.data
                    //    _showProgress.value = false


                    }

                    is ResultState.Error ->{
                      //  _showProgress.value = false
                        _error.value = result.error
                    }
                }
            }

        }

    }
}