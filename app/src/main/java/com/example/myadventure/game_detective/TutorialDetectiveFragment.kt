package com.example.myadventure.game_detective

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
import com.example.myadventure.databinding.TutorialDetectiveFragmentBinding

class TutorialDetectiveFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: TutorialDetectiveFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.tutorial_detective_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = TutorialDetectiveViewModelFactory(application, R.raw.dialoguedislexia)

        val tutorialDetectiveViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(TutorialDetectiveViewModel::class.java)

        binding.tutorialDetectiveViewModel = tutorialDetectiveViewModel

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        tutorialDetectiveViewModel.navigateToGame.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    TutorialDetectiveFragmentDirections
                        .actionTutorialDetectiveFragmentToGameDetectiveFragment()
                )
                tutorialDetectiveViewModel.doneNavigating()
            }
        })

        tutorialDetectiveViewModel.navigateToNext.observe(this, Observer { page ->
            page?.let {
                when(page){
                    0 -> binding.tutorialView.setImageResource(R.drawable.im_detective_tutorial_2)
                    1 -> binding.tutorialView.setImageResource(R.drawable.im_detective_tutorial_3)
                    2 -> binding.tutorialView.setImageResource(R.drawable.im_detective_tutorial_4)
                    3 -> binding.tutorialView.setImageResource(R.drawable.im_detective_tutorial_5)
                }

            }
        })

        return binding.root
    }
}
