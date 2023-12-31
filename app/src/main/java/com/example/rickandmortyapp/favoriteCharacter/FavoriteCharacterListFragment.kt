package com.example.rickandmortyapp.favoriteCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.data.local.FavoriteCharacterApplication
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterListBinding
import kotlinx.coroutines.launch

class FavoriteCharacterListFragment : Fragment() {

    private var _binding: FragmentFavoriteCharacterListBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView

    private val viewModel: FavoriteCharacterViewModel by activityViewModels {
        FavoriteCharacterViewModel.FavoriteCharacterViewModelFactory(
            (activity?.application as FavoriteCharacterApplication).database.characterDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentFavoriteCharacterListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            recyclerView = CharactersFavoriteListRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val favoriteCharacterAdapter = FavoriteCharacterAdapter {
                val characterId = it.id.toString()
                val navController = Navigation.findNavController(requireView())
                val action = FavoriteCharacterListFragmentDirections
                        .actionFavoriteCharacterListFragmentToFavoriteCharacterDescriptionFragment(
                            characterId
                        )
                navController.navigate(action)
            }

            recyclerView.adapter = favoriteCharacterAdapter
            lifecycle.coroutineScope.launch {
                viewModel.readAllData.collect() {
                    favoriteCharacterAdapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

