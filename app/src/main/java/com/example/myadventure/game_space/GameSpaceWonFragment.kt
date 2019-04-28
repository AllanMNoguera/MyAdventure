package com.example.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myadventure.R
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.databinding.GameSpaceWonFragmentBinding

class GameSpaceWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: GameSpaceWonFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_space_won_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val args = GameSpaceWonFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = GameSpaceWonViewModelFactory(dataSource, args.gameId)

        val gameSpaceWonViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(GameSpaceWonViewModel::class.java)

        binding.gameSpaceWonViewModel = gameSpaceWonViewModel

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
//        gameSpaceWonViewModel.navigateToGameMenu.observe(this, Observer { game ->
//            game?.let {
//                // We need to get the navController from this, because button is not ready, and it
//                // just has to be a view. For some reason, this only matters if we hit stop again
//                // after using the back button, not if we hit stop and choose a quality.
//                // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
//                // popping the stack to get the correct behavior if we press stop multiple times
//                // followed by back.
//                // Also: https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
//                ////////this.findNavController().navigate()
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                gameSpaceWonViewModel.doneNavigating()
//            }
//        })

        return binding.root
    }
}
