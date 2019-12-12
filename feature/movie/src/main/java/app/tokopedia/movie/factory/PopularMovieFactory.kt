package app.tokopedia.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.tokopedia.movie.domain.PopularMovieUseCase
import app.tokopedia.movie.ui.PopularMovieVieModel

class PopularMovieFactory
    (val useCase: PopularMovieUseCase) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  PopularMovieVieModel(useCase) as T
    }
}