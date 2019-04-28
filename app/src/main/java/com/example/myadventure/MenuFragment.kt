package com.example.myadventure


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myadventure.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)

        binding.buttonStartGameOne.setOnClickListener { v: View ->
            v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameSpaceFragment())
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}
