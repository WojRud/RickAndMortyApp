package com.example.rickandmortyapp.characterList

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.characterList.CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDescriptionFragment
import com.example.rickandmortyapp.data.network.NetworkConnection
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding
import kotlinx.coroutines.launch


class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding

 //   private lateinit var viewModel: CharacterViewModel
    private val sharedViewModel: CharacterViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter
    private var value: String? = null
    private val viewModel: CharacterViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



   //     viewModel = ViewModelProvider(this@CharactersListFragment)[CharacterViewModel::class.java]
    }

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
            val recyclerView = CharactersListRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            characterAdapter = CharacterAdapter {
                val action = actionCharactersListFragmentToCharacterDescriptionFragment(it.id)
                findNavController().navigate(action)
            }
            recyclerView.adapter = characterAdapter

            lifecycleScope.launch {
                viewModel.characterList.collect { characters ->
                    characterAdapter.submitCharacterList(characters)
                }
            }
            viewModel.getCharacterData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


