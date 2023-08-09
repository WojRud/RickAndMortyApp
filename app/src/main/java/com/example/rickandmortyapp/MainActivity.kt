package com.example.rickandmortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import com.example.rickandmortyapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //  val apiService = retrofit.create(RickAndMortyApiService::class.java)
        val AppCompatActivity: AppInterface = retrofit.create(AppInterface::class.java)

        AppCompatActivity.getCharacterById().enqueue(object : Callback<CharacterModel> {
            override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                if (response.isSuccessful) {
                    val character = response.body()
                    character?.let {
                        Log.i("MainActivity", "Name: ${it.name}")
                    }
                } else {
                    Log.i("MainActivity", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null")
                Log.e("MainActivity", "Request error: ${t.message}")
            }
        })


}
}