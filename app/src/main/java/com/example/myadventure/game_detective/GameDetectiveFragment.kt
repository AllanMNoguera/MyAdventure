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
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.databinding.GameDetectiveFragmentBinding

class GameDetectiveFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: GameDetectiveFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_detective_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = GameDetectiveViewModelFactory(dataSource, application, R.raw.bensoundenigmatic)

        val gameDetectiveViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(GameDetectiveViewModel::class.java)

        binding.gameDetectiveViewModel = gameDetectiveViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        gameDetectiveViewModel.navigateToGameScore.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    GameDetectiveFragmentDirections
                        .actionGameDetectiveFragmentToGameDetectiveWonFragment(game.gameId)
                )
                gameDetectiveViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
