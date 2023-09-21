package com.example.rickandmortyapp.favoriteCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.characterList.CharactersListFragmentDirections
import com.example.rickandmortyapp.data.FavoriteCharacterApplication
import com.example.rickandmortyapp.databinding.FragmentFavoriteCharacterListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FavoriteCharacterListFragment : Fragment() {

    private var _binding: FragmentFavoriteCharacterListBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView

    private lateinit var mFavoriteViewModel: FavoriteCharacterViewModel

    private val viewModel: FavoriteCharacterViewModel by activityViewModels {
        FavoriteCharacterViewModel.FavoriteCharacterViewModelFactory(
            (activity?.application as FavoriteCharacterApplication).database.characterDao()
        )
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
        val view = binding?.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding!!.CharactersFavoriteListRecyclerView          /////////////////////////////////////  WYKRZYKNIKI POPRAWIĆ      ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        val favoriteCharacterAdapter = FavoriteCharacterAdapter({})

        val favoriteCharacterAdapter = FavoriteCharacterAdapter {

            val navController = Navigation.findNavController(binding!!.root)
            val action =
                CharactersListFragmentDirections
                    .actionCharactersListFragmentToCharacterDescriptionFragment(
                        it.id
                    )
            navController.navigate(action)

    //        val action = FavoriteCharacterListFragmentDirections
      //          .actionFavoriteCharacterListFragmentToFavoriteCharacterDescriptionFragment(
         //           stopName = it.name
        //      )
         //   view.findNavController().navigate(action)


        }

        recyclerView.adapter = favoriteCharacterAdapter
        lifecycle.coroutineScope.launch {
            viewModel.readAllData.collect() {
                    favoriteCharacterAdapter.submitList(it)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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