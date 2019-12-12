package tokopedia.app.data.repository.movie_detail

import tokopedia.app.data.entity.Movies
import retrofit2.Response
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.MovieDetail

interface MovieRepositoryDetail {
    suspend fun getMovieDetail(movieId : String): Response<Movie>

}