package com.nepoapp.whatsappautonotification.core.services

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.Toast
import com.nepoapp.whatsappautonotification.core.manager.DataManager
import com.nepoapp.whatsappautonotification.core.utils.ActionUtils
import com.nepoapp.whatsappautonotification.core.utils.NotificationUtils.clearAllNotifications
import com.nepoapp.whatsappautonotification.core.utils.NotificationUtils.ignoreNotificationOf
import com.nepoapp.whatsappautonotification.core.utils.NotificationUtils.validateNotificationIsWhatsapp

class NotifierListener : NotificationListenerService() {

    companion object{

        @JvmStatic
        private val TAG_LOG : String = "Service Log in ${this::class.java.simpleName}"

    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        DataManager(this).setEnabledAccessNotification(enabled = true)
        Log.i(TAG_LOG,"your app are connected with service of notification ( 'StatusBar') ")
    }

    override fun onNotificationPosted(statusBar: StatusBarNotification?) {
        super.onNotificationPosted(statusBar)

        val notification = statusBar?.notification
        val extra = notification?.extras

        extra?.let {

            clearAllNotifications(this@NotifierListener,statusBar?.key)
            if (validateNotificationIsWhatsapp(it)){

                if (ignoreNotificationOf(it,"Mãe NOVO")){

                    val action = ActionUtils.getActionReply(notification)
                    val message = "Infelizmente o Lucas não se encontra online nesse momento."
                    if (action != null) {
                        ActionUtils.sendMessageWhatsapp(applicationContext, message, action!!)
                        Log.i("WHATSAPP_MESSAGE", it.getString(Notification.EXTRA_TEXT))
                    }
                }
            }
        }

    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        DataManager(this).setEnabledAccessNotification(enabled = false)
        Log.i(TAG_LOG,"your app are disconnected, enabled resource to use app ")
    }



}