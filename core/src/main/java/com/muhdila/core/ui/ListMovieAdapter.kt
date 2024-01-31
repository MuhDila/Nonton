package com.muhdila.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.muhdila.core.databinding.ItemMovieBinding
import com.muhdila.core.domain.model.Movie

class ListMovieAdapter : RecyclerView.Adapter<ListMovieAdapter.UserGithubViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listMovie.clear()
        listMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class UserGithubViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original/" + movie.poster_path)
                    .transform(RoundedCorners(20))
                    .into(ivPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGithubViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserGithubViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserGithubViewHolder, position: Int) {
        val userGithub = listMovie[position]
        holder.bind(userGithub)
    }

    override fun getItemCount(): Int = listMovie.size

}