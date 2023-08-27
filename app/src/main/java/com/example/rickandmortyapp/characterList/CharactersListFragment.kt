package com.example.rickandmortyapp.characterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.characterDescription.CharacterDescriptionViewModel
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: CharacterViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private val characterDescriptionViewModel: CharacterDescriptionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCharactersListBinding.inflate(inflater, container, false)

        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing RecyclerView
        recyclerView = binding?.CharactersListRecyclerView!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initializing adapter
        characterAdapter = CharacterAdapter { clickedCharacter ->
            val characterId = clickedCharacter.id
            val action =
                CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDescriptionFragment(
                    characterId
                )
            view.findNavController().navigate(action)
        }

        recyclerView.adapter = characterAdapter

        // Initializing ViewModel
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        // Observing changes in the character list
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitCharacterList(characters)
        }

        // Download data
        viewModel.getCharacterData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


