package app.tokopedia.movie.ui

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import app.tokopedia.movie.R
import app.tokopedia.movie.domain.PopularMovieUseCase
import app.tokopedia.movie.factory.PopularMovieFactory
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import tokopedia.app.abstraction.base.BaseFragment
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieRepository
import tokopedia.app.data.repository.movie.MovieRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.network.Network

class PopularMoviesFragment : BaseFragment() {
    override fun contentView(): Int = R.layout.fragment_popular_movies

    private lateinit var viewMovieVieModel: PopularMovieVieModel
    lateinit var useCase: PopularMovieUseCase
    lateinit var repository: MovieRepository

    val movies = mutableListOf<Movie>()
    val adapter by lazy {
        PopularMovieAdapter(movies)
    }

    override fun initObservable() {
        val networkBuilder = Network.builder().create(NetworkServices::class.java)

        repository = MovieRepositoryImpl(networkBuilder)
        useCase = PopularMovieUseCase(repository)
        viewMovieVieModel = ViewModelProviders
            .of(this,PopularMovieFactory(useCase))
            .get(PopularMovieVieModel::class.java)


    }

    override fun initView() {
        popularMovieRecyler.layoutManager = GridLayoutManager(context,2)
        popularMovieRecyler.adapter = adapter

        viewMovieVieModel.movie.observe(viewLifecycleOwner, Observer {
//            it.resultsIntent.forEach {
//                Log.d("TAG",it.title)
//
//            }
            movies.addAll(it.resultsIntent)
            adapter.notifyDataSetChanged()

        })
        viewMovieVieModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
        })
    }
}