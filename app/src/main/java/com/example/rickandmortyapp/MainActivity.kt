package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersListFragment,
                R.id.favoriteCharacterListFragment,
                R.id.favoriteCharacterListFragment
            )
        )
        val toolBar = binding.topAppBar
        toolBar.setupWithNavController(navController, appBarConfiguration)

        val bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.charactersListFragment)
                    true
                }

                R.id.navigation_favorites -> {
                    navController.navigate(R.id.favoriteCharacterListFragment)
                    true
                }

                R.id.navigation_profile -> {
                    false
                }

                else -> {
                    false
                }
            }
        }

        setContentView(binding.root)
        viewModel.getCharacterData()
    }
}

