package com.example.rickandmortyapp.characterList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.databinding.ItemCharacterBinding
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterViewModel

class CharacterAdapter(
    private val onItemClicked: (CharacterModel) -> Unit
) : ListAdapter<CharacterModel, CharacterAdapter.ViewHolder>(CharacterDiffCallback) {
    private lateinit var mCharacterViewModel: CharacterViewModel

    class ViewHolder(private val binding: ItemCharacterBinding, private var mCharacterViewModel: CharacterViewModel) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterModel) {
            binding.characterText.text = character.name
            binding.characterText.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val action =
                    CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDescriptionFragment(
                        character.id
                    )
                navController.navigate(action)
            }

//            mCharacterViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
            binding.characterAddToFavorites.setOnClickListener {

                val favCharacter = FavoriteCharacterModel(character.id, character.name, character.status, character.species, character.gender, character.image)
                mCharacterViewModel.addCharacter(favCharacter)

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
            ),
            mCharacterViewModel

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    fun submitCharacterList(characterList: List<CharacterModel>) {
        submitList(characterList)
    }
}
