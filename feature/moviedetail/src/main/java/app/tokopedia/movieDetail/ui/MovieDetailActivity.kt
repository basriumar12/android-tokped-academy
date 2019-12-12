package app.tokopedia.movieDetail.ui

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.tokopedia.movieDetail.R
import app.tokopedia.movieDetail.domain.MovieDetailUseCase
import app.tokopedia.movieDetail.factory.MovieDetailFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import tokopedia.app.abstraction.base.BaseActivity
import tokopedia.app.abstraction.util.ext.load
import tokopedia.app.data.repository.movie.MovieRepositoryImpl
import tokopedia.app.data.repository.movie_detail.MovieDetailRepositoryImpl
import tokopedia.app.data.repository.movie_detail.MovieRepositoryDetail
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.network.Network

class MovieDetailActivity : BaseActivity() {
    override fun contentView(): Int = R.layout.activity_movie_detail

    lateinit var viewModel: MovieDetailViewModel
    lateinit var useCase: MovieDetailUseCase
    lateinit var repository: MovieRepositoryDetail

    override fun initView() {
        intent.data.lastPathSegment.let {
            viewModel.setMovieId(it)
        }

        viewModel.error.observe(this, onShowerror())
        viewModel.movie.observe(this, Observer {
            imgPoster.load(it.posterUrl())
            imgBanner.load(it.bannerUrl())
            txtMovieName.text = it.title
            txtYear.text = it.releaseDate
            txtContent.text = it.overview
            txtRating.text = it.voteAverage.toString()
            txtVote.text = it.voteCount.toString()

        })
    }

    fun showMovieDetail(){

    }
    private fun onShowerror(): Observer<String> {
        return Observer { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
    }

    override fun initObservable() {

        val networkBuilder = Network.builder().create(NetworkServices::class.java)

        repository = MovieDetailRepositoryImpl(networkBuilder)
        useCase = MovieDetailUseCase(repository)
        viewModel = ViewModelProviders
            .of(this,MovieDetailFactory(useCase))
            .get(MovieDetailViewModel::class.java)

    }
}