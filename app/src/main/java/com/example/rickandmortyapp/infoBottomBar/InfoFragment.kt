package com.example.rickandmortyapp.infoBottomBar

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.rickandmortyapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding

    private var value: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentInfoBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        binding?.copyAuthorMailBtn?.setOnClickListener {
            val email = "wojciechrudol@gmail.com"
            val clipboardManager = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("label", email)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(it.context, "Copied: $email", Toast.LENGTH_SHORT).show()
        }
        return fragmentBinding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switch = binding?.modeSwitch

        switch?.setOnCheckedChangeListener{buttonView, isChecked ->

            val newTheme = if (isChecked) {
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
            AppThemes.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            AppThemes.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}