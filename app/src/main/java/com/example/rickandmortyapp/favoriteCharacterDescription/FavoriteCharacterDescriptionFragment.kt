package com.example.rickandmortyapp.favoriteCharacterDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.characterList.CharacterAdapter
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.data.FavoriteCharacterApplication
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterDescriptionBinding
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterAdapter
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterViewModel

public class FavoriteCharacterDescriptionFragment : Fragment() {

    companion object {
        var ID = "id"
    }

    private var _binding: FragmentFavoriteCharacterDescriptionBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView

    private lateinit var FavoriteCharacterAdapter: FavoriteCharacterAdapter    ////////////////////////////// PO CO??????????

    private lateinit var stopName: String

//    private val viewModel: FavoriteCharacterDescriptionViewModel by activityViewModels {
//        FavoriteCharacterDescriptionViewModel.FavoriteCharacterDescriptionViewModelFactory(
//            (activity?.application as FavoriteCharacterApplication).database.characterDao()
//        )
 //   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(ID).toString()
        }
    }


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
