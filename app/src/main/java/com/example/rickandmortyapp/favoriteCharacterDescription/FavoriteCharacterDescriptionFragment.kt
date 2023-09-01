package com.example.rickandmortyapp.favoriteCharacterDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.characterList.CharacterAdapter
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterDescriptionBinding

public class FavoriteCharacterDescriptionFragment : Fragment() {
    private var _binding: FragmentFavoriteCharacterDescriptionBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: CharacterViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFavoriteCharacterDescriptionBinding.inflate(inflater, container, false)

        _binding = fragmentBinding
        return fragmentBinding.root
    }
}
