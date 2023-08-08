package com.example.rickandmortyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyapp.databinding.FragmentCharacterslistBinding

class CharacterDescriptionFragment : Fragment() {
    private var _binding: FragmentCharacterslistBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterslistBinding.inflate(layoutInflater)
        return binding?.root
    }

}