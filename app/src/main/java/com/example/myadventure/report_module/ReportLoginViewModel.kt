package com.example.myadventure.report_module

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myadventure.database.GameDatabaseDao
import kotlinx.coroutines.Job

class ReportLoginViewModel (
    val database: GameDatabaseDao) : ViewModel() {

    private var pin = database.get(0)

    /**
     * If tonight has not been set, then the START button should be visible.
     */
    val pinMessage: LiveData<String> = Transformations.map(pin) { pin ->
        if(pin==null)
            "Please create a pin"
        else
            "Please enter pin"
    }

    private var viewModelJob = Job()
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
