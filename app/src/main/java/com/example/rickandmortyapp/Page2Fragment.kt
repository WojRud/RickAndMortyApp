package com.example.rickandmortyapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rickandmortyapp.databinding.FragmentPage2Binding

class Page2Fragment : Fragment() {

    private var _binding: FragmentPage2Binding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPage2Binding.inflate(inflater, container, false)
        _binding = fragmentBinding

        binding?.copyAuthorMailBtn?.setOnClickListener {
            val email = "wojciechrudol@gmail.com"
            val clipboardManager =
                it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("label", email)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(it.context, "Copied: $email", Toast.LENGTH_SHORT).show()
        }

        return fragmentBinding.root
    }

}