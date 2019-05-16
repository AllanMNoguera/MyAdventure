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
import com.example.myadventure.databinding.GameDetectiveWonFragmentBinding

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

        binding.gameDetectiveWonViewModel = gameDetectiveWonViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }
}
