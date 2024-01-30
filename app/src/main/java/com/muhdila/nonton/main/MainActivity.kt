package com.muhdila.nonton.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhdila.core.data.source.Resource
import com.muhdila.core.ui.ListMovieAdapter
import com.muhdila.nonton.R
import com.muhdila.nonton.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

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

        val listMovieAdapter = ListMovieAdapter()
        listMovieAdapter.onItemClick = {

        }

        mainViewModel.getListMovie.observe(this) { listMovie ->
            if (listMovie != null) {
                when (listMovie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listMovieAdapter.setData(listMovie.data)
                    }

                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            listMovie.message ?: getString(R.string.something_wrong)
                    }

                    else -> {
                        // TODO
                    }
                }
            }
        }

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listMovieAdapter
        }
    }
}