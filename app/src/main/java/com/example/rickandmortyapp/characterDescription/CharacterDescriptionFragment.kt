package com.example.rickandmortyapp.characterDescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.databinding.FragmentCharacterDescriptionBinding

class CharacterDescriptionFragment : Fragment() {
    private var _binding: FragmentCharacterDescriptionBinding? = null
    private val binding get() = _binding
    private val args: CharacterDescriptionFragmentArgs by navArgs()
    private val viewModel: CharacterDescriptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDescriptionBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = args.characterId

        viewModel.setCharacterId(characterId)

        viewModel.characterData
            .asLiveData()
            .observe(viewLifecycleOwner) { characterData: CharacterModel? ->

            binding?.textGetName?.text = characterData?.name
            binding?.textGetStatus?.text = characterData?.status
            binding?.textGetSpecies?.text = characterData?.species
            binding?.textGetGender?.text = characterData?.gender

            binding?.CharacterImage?.let { imageView ->
                Glide.with(this)
                    .load(characterData?.image)
                    .into(imageView)
            }

            binding?.addToFavBtn?.setOnClickListener {

                val favCharacter = characterData?.let { it1 ->
                    FavoriteCharacterModel(
                        it1.id,
                        characterData.name,
                        characterData.status,
                        characterData.species,
                        characterData.gender,
                        characterData.image
                    )
                }

                val message = "DODANO"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, message, duration)
                toast.show()

                if (favCharacter != null) {
                    viewModel.addCharacter(favCharacter)
                }

            }
        }
    }
}

