package com.example.rickandmortyapp.characterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.characterList.CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDescriptionFragment
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: CharacterViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter

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
        binding?.apply {

            recyclerView = CharactersListRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            characterAdapter = CharacterAdapter {
                val action = actionCharactersListFragmentToCharacterDescriptionFragment(it.id)
                view.findNavController().navigate(action)
            }
            recyclerView.adapter = characterAdapter

            viewModel = ViewModelProvider(this@CharactersListFragment)[CharacterViewModel::class.java]

            viewModel.characterList.observe(viewLifecycleOwner) { characters ->
                characterAdapter.submitCharacterList(characters)
            }
            viewModel.getCharacterData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


