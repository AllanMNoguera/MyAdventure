package com.example.myadventure


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        binding.buttonStartGameOne.setOnClickListener { v: View ->
            if(dataSource.getSpaceLatestScores().isEmpty()){
                v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialSpaceFragment())
            } else {
                v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameSpaceFragment())
            }
        }

        binding.buttonTutorialSpace.setOnClickListener { v: View ->
            v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialSpaceFragment())
        }

        binding.buttonStartGameTwo.setOnClickListener { v: View ->
            if(dataSource.getDetectiveLatestScores().isEmpty()) {
                v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialDetectiveFragment())
            } else {
                v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToGameDetectiveFragment())
            }
        }

        binding.buttonTutorialDetective.setOnClickListener { v: View ->
            v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialDetectiveFragment())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
