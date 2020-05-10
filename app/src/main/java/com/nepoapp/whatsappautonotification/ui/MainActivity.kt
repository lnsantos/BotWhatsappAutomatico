package com.nepoapp.whatsappautonotification.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nepoapp.whatsappautonotification.R
import com.nepoapp.whatsappautonotification.databinding.ActivityMainBinding
import com.nepoapp.whatsappautonotification.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var vmodel : MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    override fun onResume() {
        super.onResume()
        setupView()
        binding.buttonActionOpenPermission.setOnClickListener {
            vmodel.startNotificationSetting(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        vmodel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        vmodel.accessNotification.observe(this,observerAccessNotification)
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

    }

    private val observerAccessNotification = Observer<Boolean>{ isEnabled ->
        if (isEnabled) handlerAccessNotificationGrated()
        else handlerAccessNotificationDenied()
    }

    private fun handlerAccessNotificationDenied(){
        binding.textAlertPermission.text = resources.getString(R.string.text_title_permission_denied)
        binding.textAlertDetailsPermission.text = resources.getString(R.string.main_notification_message_alert_permission)
        binding.buttonActionOpenPermission.isEnabled = true
    }

    private fun handlerAccessNotificationGrated(){
        binding.textAlertPermission.apply {
            text = "PERMISSÃO HABILITADA"
            setTextColor(Color.GREEN)
        }
        binding.buttonActionOpenPermission.text = "Desabilitar permissão"
        binding.textAlertDetailsPermission.text = "Sua permissão foi habilitada com sucesso!"
    }

}
