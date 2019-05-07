package com.example.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.myadventure.R
import com.example.myadventure.databinding.TutorialSpaceFragmentBinding

class TutorialSpaceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: TutorialSpaceFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.tutorial_space_fragment, container, false)

        val viewModelFactory = TutorialSpaceViewModelFactory()

        val tutorialSpaceViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(TutorialSpaceViewModel::class.java)

        binding.tutorialSpaceViewModel = tutorialSpaceViewModel

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        tutorialSpaceViewModel.navigateToGame.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    TutorialSpaceFragmentDirections
                        .actionTutorialSpaceFragmentToGameDetectiveFragment()
                )
                tutorialSpaceViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
