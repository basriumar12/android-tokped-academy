package tokopedia.app.data.repository.movie_detail

import retrofit2.Response
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.MovieDetail
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.routes.NetworkServices

class MovieDetailRepositoryImpl constructor(
    private val service: NetworkServices
): MovieRepositoryDetail {
    override suspend fun getMovieDetail(movieId: String): Response<Movie> {
        return service.getMovieDetail(movieId)
    }


}