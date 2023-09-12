package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmortyapp.characterList.CharacterViewModel
import com.example.rickandmortyapp.characterList.CharactersListFragment
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterListFragment
import com.example.rickandmortyapp.settings.TestTest

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
                R.id.characterDescriptionFragment,
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
                    navController.navigate(R.id.characterDescriptionFragment)
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


        /*
        replaceFragment(CharactersListFragment())

        binding.bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.navigation_home -> replaceFragment(CharactersListFragment())
                R.id.navigation_favorites -> replaceFragment(FavoriteCharacterListFragment())
          //      R.id.navigation_profile -> replaceFragment(TestTest())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()

    }


         */

        /*



                       val charactersListFragment = CharactersListFragment()
                       val favoriteCharacterListFragment = FavoriteCharacterListFragment()
                       val settingsFragment = SettingsFragment()

                    //   val bottomNavigationView = binding.bottom_nav
                     //  bottomNavigationView.setupWithNavController(navController)

                       val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
                       bottomNav.selectedItemId = R.id.navigation_home
                       bottomNav.selectedItemId = R.id.navigation_favorites
                       bottomNav.selectedItemId = R.id.navigation_profile

                       fun setCurrFragment(fragment : Fragment){
                           supportFragmentManager.beginTransaction().apply {
                               replace(R.id.nav_host_fragment,fragment)
                               commit()
                           }
                       }

                       bottomNav.setOnItemSelectedListener {
                           when(it.itemId){

                               R.id.navigation_home ->
                                   setCurrFragment(charactersListFragment)
                               R.id.navigation_favorites ->
                                   setCurrFragment(favoriteCharacterListFragment)
                   //            R.id.navigation_profile ->
                     //              setCurrFragment(settingsFragment)

                           }
                           true
                       }





















                */













        setContentView(binding.root)
        viewModel.getCharacterData()

    }
}

