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

class CharacterAdapter(
    private val onItemClicked: (CharacterModel) -> Unit,
) : ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterDiffCallback) {
    // class CharacterAdapter(private val itemList: List<CharacterModel>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.character_text)

        fun bind(character: CharacterModel) {
            nameTextView.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        val viewHolder = ViewHolder(itemView)
 //       return ViewHolder(itemView)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClicked(getItem(position))
            }
        }

        return viewHolder

 /*
        ViewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return ViewHolder


  */

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
     //   holder.nameTextView.text = currentItem.name
    }

    fun submitCharacterList(characterList: List<CharacterModel>) {
        submitList(characterList)
    }

    companion object CharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }
}
























/*

class CharacterAdapter(private val onClick: (CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(FlowerDiffCallback) {

    class ViewHolder(itemView: View, val onClick: (CharacterModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val characterTextView: TextView = itemView.findViewById(R.id.character_text)
        private var currentCharacter: CharacterModel? = null

        init {
            itemView.setOnClickListener {
                currentCharacter?.let {
                    onClick(it)
                }
            }
        }

        fun bind(characterModel: CharacterModel) {
            currentCharacter = characterModel

            characterTextView.text = characterModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterModel = getItem(position)
        holder.bind(characterModel)

    }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.id == newItem.id
    }
}
*///