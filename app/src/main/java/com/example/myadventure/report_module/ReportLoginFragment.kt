package com.example.myadventure.report_module

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.myadventure.R
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.databinding.ReportLoginFragmentBinding

class ReportLoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: ReportLoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.report_login_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = ReportLoginViewModelFactory(dataSource)

        val reportLoginViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(ReportLoginViewModel::class.java)

        binding.reportLoginViewModel = reportLoginViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
//        viewModelFactory.navigateToGameScore.observe(this, Observer { game ->
//            game?.let {
//                this.findNavController().navigate(
//                    GameSpaceFragmentDirections
//                        .actionGameSpaceFragmentToGameSpaceWonFragment(game.gameId)
//                )
//                gameSpaceViewModel.doneNavigating()
//            }
//        })

        return binding.root
    }
}
