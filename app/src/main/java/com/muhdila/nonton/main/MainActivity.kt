package com.muhdila.nonton.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.muhdila.core.data.source.Resource
import com.muhdila.core.domain.model.Movie
import com.muhdila.core.ui.ListMovieAdapter
import com.muhdila.nonton.R
import com.muhdila.nonton.databinding.ActivityMainBinding
import com.muhdila.nonton.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val listMovieAdapter = ListMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.layoutSearch.imgSetting.setOnClickListener {
            Toast.makeText(this@MainActivity, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        binding.layoutSearch.imgFav.setOnClickListener {
            val uri = Uri.parse("nonton://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        listMovieAdapter.onItemClick = { movie ->
            Intent(this@MainActivity, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }

        mainViewModel.getListMovie.observe(this@MainActivity) { listMovie ->
            handleMovieListResult(listMovie, listMovieAdapter)
        }

        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = listMovieAdapter
        }

        setupSearchBar()
    }

    private fun handleMovieListResult(result: Resource<List<Movie>>?, adapter: ListMovieAdapter) {
        if (result != null) {
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.setData(result.data)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        result.message ?: getString(R.string.something_wrong)
                }
            }
        }
    }

    // TODO Search
    private fun setupSearchBar() {
        val searchBar = binding.layoutSearch.searchBar
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    val textSearchBar = searchView.text
                    mainViewModel.searchMovieResult(textSearchBar.toString()).observe(this@MainActivity) { searchResult ->
                        handleMovieListResult(searchResult, listMovieAdapter)
                    }
                    searchView.hide()
                    true
                }
        }
    }
}