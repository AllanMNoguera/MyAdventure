package com.coffee.myadventure


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.coffee.myadventure.database.GameDatabase
import com.coffee.myadventure.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_menu, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = MenuViewModelFactory(dataSource)

        val menuViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(MenuViewModel::class.java)

        binding.buttonTutorialSpace.setOnClickListener { v: View ->
            v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialSpaceFragment())
        }

        binding.buttonTutorialDetective.setOnClickListener { v: View ->
            v.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToTutorialDetectiveFragment())
        }

        menuViewModel.navigateToGameOne.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToGameSpaceFragment()
                )
                menuViewModel.doneNavigating()
            }
        })

        menuViewModel.navigateToGameTwo.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToGameDetectiveFragment()
                )
                menuViewModel.doneNavigating()
            }
        })

        menuViewModel.navigateToGameTutorialOne.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToTutorialSpaceFragment()
                )
                menuViewModel.doneNavigating()
            }
        })

        menuViewModel.navigateToGameTutorialTwo.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToTutorialDetectiveFragment()
                )
                menuViewModel.doneNavigating()
            }
        })

        binding.menuViewModel = menuViewModel

        binding.setLifecycleOwner(this)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
