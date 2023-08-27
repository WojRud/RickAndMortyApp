package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.topMenu.TopMenuFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<TopMenuFragment>(R.id.topMenuFragment)

        }

        viewModel.getCharacterData()

    }

}
