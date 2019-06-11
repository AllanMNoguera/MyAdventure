package com.coffee.myadventure.report_module

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.coffee.myadventure.database.GameDatabaseDao
import com.coffee.myadventure.database.Pin
import kotlinx.coroutines.*

class ReportLoginViewModel (
    val database: GameDatabaseDao,
    var editText: EditText) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var pin:LiveData<Pin?> = database.getPin()
    private var inputPin: Pin? = null
    private var newPin: Pin? = null

    /**
     * If tonight has not been set, then the START button should be visible.
     */
    val pinMessage: LiveData<String> = Transformations.map(pin) { pin ->
        if(pin==null) {
            "Crear un pin de 5 digitos"
        } else {
            "Ingresa tu pin"
        }
    }

    private val _navigateToReport = MutableLiveData<Pin?>()

    val navigateToReport: LiveData<Pin?>
        get() = _navigateToReport

    fun doneNavigating() {
        _navigateToReport.value = null
    }

    private suspend fun insert(newPin: Pin): Long {
        return withContext(Dispatchers.IO) {
            var pinId:Long = database.insert(newPin)
            pinId
        }
    }

    fun pinRead() {
        if (editText.text.toString().length >= 5) {
            if(pin.value == null && newPin == null) {
                newPin = Pin(pin = editText.text.toString().toInt())
            } else if (inputPin == null && pin.value != null) {
                System.out.println(pin.value)
                inputPin = Pin(pin = editText.text.toString().toInt())
                if(pin.value!!.pin == inputPin!!.pin) {
                    _navigateToReport.value = inputPin
                    inputPin = null
                } else {
                    inputPin = null
                }
            } else if (newPin != null) {
                inputPin = Pin(pin = editText.text.toString().toInt())
                if (newPin!!.pin == inputPin!!.pin) {
                    uiScope.launch {
                        val pinInsert = newPin ?: return@launch
                        insert(pinInsert)
                    }
                    _navigateToReport.value = newPin
                } else {
                    newPin = null
                    inputPin = null
                }
            }
            editText.text.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
