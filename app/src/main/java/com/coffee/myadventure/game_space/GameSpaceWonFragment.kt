package com.coffee.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.coffee.myadventure.R
import com.coffee.myadventure.database.GameDatabase
import com.coffee.myadventure.databinding.GameSpaceWonFragmentBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

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

        val viewModelFactory = GameSpaceWonViewModelFactory(dataSource, args.gameId, application, R.raw.jingleachievement)

        val gameSpaceWonViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(GameSpaceWonViewModel::class.java)

        gameSpaceWonViewModel.navigateToGameMenu.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    GameSpaceWonFragmentDirections.actionGameSpaceWonFragmentToMenuFragment()
                )
                gameSpaceWonViewModel.doneNavigating()
            }
        })

        binding.gameSpaceWonViewModel = gameSpaceWonViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(5))
            withContext(Dispatchers.Main) {
                view!!.findNavController().navigate(
                    GameSpaceWonFragmentDirections.actionGameSpaceWonFragmentToMenuFragment()
                )
            }
        }
    }
}
