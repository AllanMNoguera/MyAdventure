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
import com.coffee.myadventure.databinding.GameDetectiveWonFragmentBinding
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

class GameDetectiveWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: GameDetectiveWonFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_detective_won_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val args = GameSpaceWonFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = GameSpaceWonViewModelFactory(dataSource, args.gameId, application, R.raw.jingleachievement)

        val gameDetectiveWonViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(GameSpaceWonViewModel::class.java)

        gameDetectiveWonViewModel.navigateToGameMenu.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    GameDetectiveWonFragmentDirections.actionGameDetectiveWonFragmentToMenuFragment()
                )
                gameDetectiveWonViewModel.doneNavigating()
            }
        })

        binding.gameDetectiveWonViewModel = gameDetectiveWonViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(5))
            withContext(Dispatchers.Main) {
                try {
                    view!!.findNavController().navigate(
                        GameDetectiveWonFragmentDirections.actionGameDetectiveWonFragmentToMenuFragment()
                    )
                } catch (ignore:Exception){}
            }
        }
    }
}
