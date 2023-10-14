package com.example.rickandmortyapp.characterList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortyapp.data.network.CharacterModel
import com.example.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClicked: (CharacterModel) -> Unit
) : ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterDiffCallback) {

    class ViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel) {
            binding.characterText.text = character.name
            binding.characterText.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val action =
                    CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDescriptionFragment(
                            character.id
                        )
                navController.navigate(action)
            }
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
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    fun submitCharacterList(character: List<CharacterModel>) {
        submitList(character)
    }
}
