package com.example.rickandmortyapp.infoBottomBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.rickandmortyapp.Page1Fragment
import com.example.rickandmortyapp.Page2Fragment
import com.example.rickandmortyapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding

    private var value: String? = null

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentInfoBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        viewPager = _binding!!.viewPager

        val fragments: ArrayList<Fragment> = arrayListOf(
            Page1Fragment(),
            Page2Fragment()
        )

        val adapter = ViewPageAdapter(fragments, this)
        viewPager.adapter = adapter




        return fragmentBinding.root
    }


}

