package com.coffee.myadventure.about_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.coffee.myadventure.R
import com.coffee.myadventure.databinding.AboutFragmentBinding
class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AboutFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.about_fragment, container, false)

        return binding.root
    }

}
