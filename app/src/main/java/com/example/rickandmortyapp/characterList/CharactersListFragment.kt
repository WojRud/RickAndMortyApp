package com.example.rickandmortyapp.characterList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.databinding.FragmentCharactersListBinding

class CharactersListFragment : Fragment() {
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: CharacterViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersListBinding.inflate(layoutInflater)
        return binding?.root
    }

  //  recyclerView = binding.recyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  characterAdapter = CharacterAdapter()

        // Inicjalizacja RecyclerView
        recyclerView = binding?.CharactersListRecyclerView!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Inicjalizacja adaptera
        characterAdapter = CharacterAdapter()
        recyclerView.adapter = characterAdapter

        // Inicjalizacja ViewModel
        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        // Obserwacja zmian w liście postaci
        viewModel.characterList.observe(viewLifecycleOwner) { characters ->
            characterAdapter.submitCharacterList(characters)
        }

        // Pobranie danych
        viewModel.getCharacterData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




/*
    private val newFlowerActivityRequestCode = 1
    private val characterViewModel by viewModels<CharacterViewModel> {
        CharactersListViewModelFactory(this)
    }

      //  binding?.GoToDescBtn?.setOnClickListener {
      //      findNavController().navigate(R.id.action_charactersListFragment_to_characterDescriptionFragment)
      //  }


 */