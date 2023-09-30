package com.example.rickandmortyapp.favoriteCharacterDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.data.CharacterDao
import com.example.rickandmortyapp.data.FavoriteCharacterApplication
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterDescriptionBinding
import kotlinx.coroutines.launch

class FavoriteCharacterDescriptionFragment : Fragment() {

    private val args: FavoriteCharacterDescriptionFragmentArgs by navArgs()

    private lateinit var viewModel: FavoriteCharacterDescriptionViewModel

    private var _binding: FragmentFavoriteCharacterDescriptionBinding? = null

    private lateinit var characterDao: CharacterDao

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding =
            FragmentFavoriteCharacterDescriptionBinding.inflate(inflater, container, false)

        _binding = fragmentBinding

        val favoriteCharacterId = args.favoriteCharacterId.toInt()

        characterDao =
            (activity?.application as FavoriteCharacterApplication).database.characterDao()

        val viewModelFactory = FavoriteCharacterDescriptionViewModelFactory(characterDao)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[FavoriteCharacterDescriptionViewModel::class.java]

        viewModel.getCharacterById(favoriteCharacterId)
            .asLiveData()
            .observe(viewLifecycleOwner) { favoriteCharacterDetails: FavoriteCharacterModel?  ->

                binding?.textGetName?.text = favoriteCharacterDetails?.name
                binding?.textGetStatus?.text = favoriteCharacterDetails?.status
                binding?.textGetSpecies?.text = favoriteCharacterDetails?.species
                binding?.textGetGender?.text = favoriteCharacterDetails?.gender

                binding?.CharacterImage?.let { imageView ->
                    Glide.with(this)
                        .load(favoriteCharacterDetails?.image)
                        .into(imageView)
                }

                binding?.deleteFavBtn?.setOnClickListener {

                    lifecycleScope.launch {
                        characterDao.deleteCharacterById(favoriteCharacterId)

                        val navControllers =
                            Navigation.findNavController(binding!!.root)    /////////////////////////////////////  WYKRZYKNIKI POPRAWIĆ      ///!!!!!!!!!!!!!!!!!!!!!!!!!!!////////////////////!!!!!!!!!!!!!
                        val action =
                            FavoriteCharacterDescriptionFragmentDirections
                                .actionFavoriteCharacterDescriptionFragmentToFavoriteCharacterListFragment(

                                )
                        navControllers.navigate(action)
                    }
                }
            }
        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

