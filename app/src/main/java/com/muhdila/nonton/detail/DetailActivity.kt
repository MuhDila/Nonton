package com.muhdila.nonton.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.muhdila.core.domain.model.Movie
import com.muhdila.nonton.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.detail) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detailListMovie = intent.getParcelableExtra<Movie>(DATA)
        showDetailListMovie(detailListMovie)

    }

    private fun showDetailListMovie(detailListMovie: Movie?) {
        detailListMovie?.let {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original/" + detailListMovie.backdrop_path)
                    .transform(RoundedCorners(20))
                    .into(backgroundImage)

                genresText.text = detailListMovie.vote_average.toString()

                var favStatus = detailListMovie.isFavorite
//                setStatusFavorite(favStatus)
//                favoriteButton.setOnClickListener {
//                    favStatus = !favStatus
//                    detailViewModel.setFavoriteUserGithub(detailListMovie, favStatus)
//                    setStatusFavorite(favStatus)
//                }
            }
        }
    }

//    private fun setStatusFavorite(statusFav: Boolean) {
//        if (statusFav) {
//            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_fill))
//        } else {
//            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_border))
//        }
//    }

    companion object {
        const val DATA = "data"
    }

}