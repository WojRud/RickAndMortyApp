package com.example.rickandmortyapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
 //   private lateinit var viewModel: CharacterViewModel
 //   private lateinit var charRepository: CharRepository
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val topMenuFragment = TopMenuFragment()
        val recyclerFragment = CharactersListFragment()

        supportFragmentManager.commit {
            addToBackStack(null)
            add(R.id.fragmentContainer, topMenuFragment, null)
            add(R.id.recyclerContainer, recyclerFragment, null)
        }

       // charRepository = CharRepository()

     //   viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(getApplication())).get(CharacterViewModel::class.java)

        viewModel.getCharacterData()


    }
}
