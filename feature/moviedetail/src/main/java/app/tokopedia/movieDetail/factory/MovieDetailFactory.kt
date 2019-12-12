package app.tokopedia.movieDetail.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.tokopedia.movieDetail.domain.MovieDetailUseCase
import app.tokopedia.movieDetail.ui.MovieDetailViewModel

class MovieDetailFactory (val useCase: MovieDetailUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(useCase) as T
    }
}