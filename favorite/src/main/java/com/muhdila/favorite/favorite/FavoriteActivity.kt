package com.muhdila.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.muhdila.core.ui.ListMovieAdapter
import com.muhdila.favorite.databinding.ActivityFavoriteBinding
import com.muhdila.favorite.di.favoriteModule
import com.muhdila.nonton.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.favorite) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadKoinModules(favoriteModule)

        val listMovieAdapter = ListMovieAdapter()
        listMovieAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }

        favoriteViewModel.getListFavoriteMovie.observe(this) {
            listMovieAdapter.setData(it)
            binding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        binding.rvMovie.apply {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            adapter = listMovieAdapter
            setHasFixedSize(true)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}