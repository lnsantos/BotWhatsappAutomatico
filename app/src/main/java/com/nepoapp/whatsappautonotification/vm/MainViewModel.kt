package com.nepoapp.whatsappautonotification.vm

import android.app.Application
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nepoapp.whatsappautonotification.core.manager.DataManager

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val _accessNotification = MutableLiveData<Boolean>()

    val accessNotification : LiveData<Boolean> = _accessNotification

    init {
        validateAccessNotification()
    }

    fun startNotificationSetting(context: Context){
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        context.startActivity(intent)
        validateAccessNotification()
    }

    private fun validateAccessNotification(){
        if (DataManager(getApplication()).getEnabledAccessNotification()){
            _accessNotification.postValue(true)
        }
    }


}