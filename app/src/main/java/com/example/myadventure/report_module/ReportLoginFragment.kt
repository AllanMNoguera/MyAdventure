package com.example.myadventure.report_module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.myadventure.R
import com.example.myadventure.database.GameDatabase
import com.example.myadventure.database.Pin
import com.example.myadventure.databinding.ReportLoginFragmentBinding

class ReportLoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: ReportLoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.report_login_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = ReportLoginViewModelFactory(dataSource, binding.pinEdit)

        val reportLoginViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(ReportLoginViewModel::class.java)

        binding.reportLoginViewModel = reportLoginViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        reportLoginViewModel.navigateToReport.observe(this, Observer { pin: Pin? ->
            pin?.let {
                this.findNavController().navigate(
                    ReportLoginFragmentDirections
                        .actionReportLoginFragmentToReportFragment()
                )
                reportLoginViewModel.doneNavigating()
                binding.pinEdit.hideKeyboard()
            }
        })

        return binding.root
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
