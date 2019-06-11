package com.coffee.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.coffee.myadventure.R
import com.coffee.myadventure.database.GameDatabase
import com.coffee.myadventure.databinding.GameSpaceFragmentBinding

class GameSpaceFragment : Fragment() {

    private lateinit var gameSpaceViewModel: GameSpaceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: GameSpaceFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_space_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = GameSpaceViewModelFactory(dataSource, application, R.raw.bensoundadventure)

        gameSpaceViewModel =
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

        gameSpaceViewModel.questionString.observe(this, Observer { image ->
            image?.let {
                when(image){
                    "6" -> binding.questionView.setBackgroundResource(R.drawable.im_space_1)
                    "12" -> binding.questionView.setBackgroundResource(R.drawable.im_space_2)
                    "2" -> binding.questionView.setBackgroundResource(R.drawable.im_space_3)
                    "42" -> binding.questionView.setBackgroundResource(R.drawable.im_space_4)
                    "74" -> binding.questionView.setBackgroundResource(R.drawable.im_space_5)
                }
            }
        })

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        gameSpaceViewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        gameSpaceViewModel.onResume()
    }
}
