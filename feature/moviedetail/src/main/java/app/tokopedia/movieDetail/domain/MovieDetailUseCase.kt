package app.tokopedia.movieDetail.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie_detail.MovieRepositoryDetail

class MovieDetailUseCase (
    private  val repository : MovieRepositoryDetail)  {
    suspend fun get(idMovie : String) : ResultState<Movie> {
        val response  = repository.getMovieDetail(idMovie)
        return if (response.isSuccessful){

            ResultState.Success(response.body()!!)
        } else{
            ResultState.Error(MOVIE_ERROR)
        }

    }
    companion object{
        private const val MOVIE_ERROR ="aduh, get movienya gagal"
    }


}