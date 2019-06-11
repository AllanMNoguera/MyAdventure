package com.coffee.myadventure.report_module

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.coffee.myadventure.R
import com.coffee.myadventure.database.GameDatabase
import com.coffee.myadventure.databinding.ReportFragmentBinding

class ReportFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: ReportFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.report_fragment, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = ReportViewModelFactory(dataSource, binding.barChartSpaceGame, binding.barChartDetectiveGame)

        val reportViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(ReportViewModel::class.java)

        binding.reportViewModel = reportViewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }
}