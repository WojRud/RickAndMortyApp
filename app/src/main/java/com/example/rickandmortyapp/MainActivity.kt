package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyapp.characterDescription.CharacterDescriptionFragment
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.characterList.CharactersListFragment
import com.example.rickandmortyapp.data.network.NetworkConnection
import com.example.rickandmortyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CharacterViewModel by viewModels()

    private var myFragment: CharactersListFragment? = null
    private var mydescFragment: CharacterDescriptionFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inflateLayout = findViewById<View>(R.id.networkError)
        val inflateLayoutSecound = findViewById<View>(R.id.CharactersListRecyclerView)
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this) {
            if (it) {
                Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        //        inflateLayout.visibility = View.GONE
            } else {
                Toast.makeText(this, "CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_SHORT).show()
        //        inflateLayout.visibility = View.VISIBLE
      //          inflateLayoutSecound.visibility = View.GONE
            }
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.charactersListFragment,
                R.id.favoriteCharacterListFragment,
                R.id.infoFragment
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
                    navController.navigate(R.id.infoFragment)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }
}

