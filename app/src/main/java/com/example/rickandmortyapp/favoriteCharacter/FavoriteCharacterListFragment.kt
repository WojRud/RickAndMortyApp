package com.example.rickandmortyapp.favoriteCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.data.FavoriteCharacterApplication
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterListBinding
import kotlinx.coroutines.launch


class FavoriteCharacterListFragment : Fragment() {

    companion object {
        var CHAR_NAME = "name"
    }

    private var _binding: FragmentFavoriteCharacterListBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView

    private lateinit var mFavoriteViewModel: FavoriteCharacterViewModel

    private lateinit var name: String

    private val viewModel: FavoriteCharacterViewModel by activityViewModels {
        FavoriteCharacterViewModel.FavoriteCharacterViewModelFactory(
            (activity?.application as FavoriteCharacterApplication).database.characterDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString(CHAR_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        //   val fragmentBinding = FragmentFavoriteCharacterListBinding.inflate(inflater, container, false)

        _binding = FragmentFavoriteCharacterListBinding.inflate(inflater, container, false)
        //       val view = binding?.root
        //     return view
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.CharactersFavoriteListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        val favoriteCharacterAdapter = FavoriteCharacterAdapter({})

        val favoriteCharacterAdapter = FavoriteCharacterAdapter {
            val action = FavoriteCharacterListFragmentDirections
                .actionFavoriteCharacterListFragmentToFavoriteCharacterDescriptionFragment(        //   action_favoriteCharacterListFragment_to_favoriteCharacterDescriptionFragment
                    stopName = it.name
                )
            view.findNavController().navigate(action)


            // by passing in the stop name, filtered results are returned,
            // and tapping rows won't trigger navigation
            recyclerView.adapter = favoriteCharacterAdapter
            lifecycle.coroutineScope.launch {
                viewModel.fullSchedule(name)
                    .collect() {           ///////////////////////// Z VIEWMODELA
                        favoriteCharacterAdapter.submitList(it)
                    }
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }
}













/*
        val adapter = FavoriteCharacterAdapter()
        //    val recyclerView: view.recyclerview
        val recyclerView = fragmentBinding.recyclerview

        val recyclerVIEW = view.rec

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mFavoriteViewModel = ViewModelProvider(this)[FavoriteCharacterViewModel::class.java]
        mFavoriteViewModel.readAllData.observe(viewLifecycleOwner, Observer { favoriteCharacterModel ->
            adapter.setData(favoriteCharacterModel)
        })

 */

//      _binding = fragmentBinding
//    return fragmentBinding.root







/*
class FavoriteCharacterListFragment : Fragment() {
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        recyclerView = view.findViewById(R.id.recyclerview)
        adapter = ItemAdapter(emptyList()) // Pusty adapter na początek
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        itemViewModel = ViewModelProvider(this, ItemViewModelFactory(itemDao)).get(ItemViewModel::class.java)

        itemViewModel.allItems.observe(viewLifecycleOwner, Observer { items ->
            // Aktualizuj adapter za pomocą nowych danych
            adapter.setItems(items)
        })

        return view
    }
}











class FavoriteCharacterListFragment : Fragment() {
    private var _binding: FragmentFavoriteCharacterListBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteCharacterAdapter
    private lateinit var viewModel: FavoriteCharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFavoriteCharacterListBinding.inflate(inflater, container, false)

        _binding = fragmentBinding
        return fragmentBinding.root
    }

    val adapter = FavoriteCharacterAdapter()
    val recyclerView = view.recyclerview
    recyclerView.adapter = adapter


}

 */