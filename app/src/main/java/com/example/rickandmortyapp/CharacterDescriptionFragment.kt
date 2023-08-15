package com.example.rickandmortyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.FragmentCharacterDescriptionBinding

class CharacterDescriptionFragment : Fragment() {
    private var _binding: FragmentCharacterDescriptionBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDescriptionBinding.inflate(layoutInflater)
        return binding?.root
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

   //     val viewModel = ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
        viewModel.characterLiveData.observe(viewLifecycleOwner) { characterInfo ->
            binding?.textGetName?.text = characterInfo.name
            binding?.textGetStatus?.text = characterInfo.status
            binding?.textGetSpecies?.text = characterInfo.species
            binding?.textGetGender?.text = characterInfo.gender
        } */
    }


