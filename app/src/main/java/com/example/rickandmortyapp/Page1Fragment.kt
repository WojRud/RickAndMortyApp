package com.example.rickandmortyapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.example.rickandmortyapp.databinding.FragmentPage1Binding
import com.example.rickandmortyapp.infoBottomBar.AppThemes

class Page1Fragment : Fragment() {
    private var _binding: FragmentPage1Binding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPage1Binding.inflate(inflater, container, false)
        _binding = fragmentBinding

        return fragmentBinding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val changeModeBtn = binding?.modeBtn
        var isDayMode = true

        changeModeBtn?.setOnClickListener { view: View ->

            isDayMode = !isDayMode

            val newTheme = if (isDayMode) {
                AppThemes.DAY
            } else {
                AppThemes.NIGHT
            }
            setAppTheme(newTheme)
        }
    }

    private fun setAppTheme(theme: AppThemes) {
        when (theme) {
            AppThemes.DAY -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppThemes.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }


    }

}