package com.example.rickandmortyapp.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClicked: (CharacterModel) -> Unit,
) : ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterDiffCallback) {

    class ViewHolder(private var binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameTextView = binding.characterText
        fun bind(
            character: CharacterModel
        ) {
            binding.characterText.text = character.name
        }
    }

    companion object CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        viewHolder.itemView.setOnLongClickListener {
            true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.nameTextView.text = currentItem.name
    }

    fun submitCharacterList(characterList: List<CharacterModel>) {
        submitList(characterList)
    }
}
