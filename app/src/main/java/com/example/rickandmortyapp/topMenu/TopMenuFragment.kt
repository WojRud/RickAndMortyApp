package com.example.rickandmortyapp.topMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyapp.databinding.FragmentTopMenuBinding

class TopMenuFragment : Fragment() {
    private var _binding: FragmentTopMenuBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopMenuBinding.inflate(layoutInflater)
        return binding?.root
    }






}