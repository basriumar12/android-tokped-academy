package tokopedia.app.jetmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.tokopedia.movie.ui.PopularMoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateFragment()
    }

    private fun inflateFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, PopularMoviesFragment())
        transaction.commit()
    }
}
