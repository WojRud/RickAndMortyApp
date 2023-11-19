package com.example.rickandmortyapp.favoriteCharacter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.data.local.FavoriteCharacterModel
import com.example.rickandmortyapp.databinding.ItemCharacterBinding

class FavoriteCharacterAdapter(
    private val onItemClicked: (FavoriteCharacterModel) -> Unit
) : ListAdapter<FavoriteCharacterModel, FavoriteCharacterAdapter.FavCharViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FavoriteCharacterModel>() {
            override fun areItemsTheSame(oldItem: FavoriteCharacterModel, newItem: FavoriteCharacterModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteCharacterModel, newItem: FavoriteCharacterModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCharViewHolder {
        val viewHolder = FavCharViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.binding.characterText.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FavCharViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavCharViewHolder(
        val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favCharacterModel: FavoriteCharacterModel) {
            binding.characterText.text = favCharacterModel.name
        }
    }
}



