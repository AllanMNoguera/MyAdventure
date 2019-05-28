package com.example.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myadventure.R
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.databinding.GameSpaceFragmentBinding

class GameSpaceFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: GameSpaceFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_space_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = GameSpaceViewModelFactory(dataSource, application, R.raw.bensoundadventure)

        val gameSpaceViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(GameSpaceViewModel::class.java)

        binding.gameSpaceViewModel = gameSpaceViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        gameSpaceViewModel.navigateToGameScore.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    GameSpaceFragmentDirections
                        .actionGameSpaceFragmentToGameSpaceWonFragment(game.gameId)
                )
                gameSpaceViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
