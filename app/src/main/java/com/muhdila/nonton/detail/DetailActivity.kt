package com.muhdila.nonton.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.muhdila.core.domain.model.Movie
import com.muhdila.nonton.R
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

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnPlay.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        binding.iconDownload.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        val detailListMovie = intent.getParcelableExtra<Movie>(DATA)
        showDetailListMovie(detailListMovie)

    }

    @SuppressLint("SetTextI18n")
    private fun showDetailListMovie(detailListMovie: Movie?) {
        detailListMovie?.let {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/original/" + detailListMovie.backdrop_path)
                    .transform(RoundedCorners(20))
                    .into(backgroundImage)

                tvTitle.text = detailListMovie.title
                tvRating.text = "IMDB ${detailListMovie.vote_average.toString()}"
                tvReleaseDate.text = detailListMovie.release_date
                tvPopularity.text = detailListMovie.popularity.toString()
                tvOverview.text = detailListMovie.overview

                var favStatus = detailListMovie.isFavorite
                setStatusFavorite(favStatus)

                iconFavorite.setOnClickListener {
                    favStatus = !favStatus
                    detailViewModel.setFavoriteListMovie(detailListMovie, favStatus)
                    setStatusFavorite(favStatus)
                    showFavoriteStatusMessage(favStatus)
                }

            }
        }
    }

    private fun setStatusFavorite(statusFav: Boolean) {
        if (statusFav) {
            binding.iconFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_filled))
        } else {
            binding.iconFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_outline))
        }
    }

    private fun showFavoriteStatusMessage(isFavorite: Boolean) {
        val message = if (isFavorite) "Added to Favorites" else "Removed from Favorites"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val DATA = "data"
    }

}