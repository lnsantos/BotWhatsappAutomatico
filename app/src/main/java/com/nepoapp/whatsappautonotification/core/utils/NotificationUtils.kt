package com.nepoapp.whatsappautonotification.core.utils

import android.app.Notification
import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.nepoapp.whatsappautonotification.core.manager.DataManager
import com.nepoapp.whatsappautonotification.core.services.NotifierListener
import java.util.*

object NotificationUtils {
     const val APPLICATION_INFO = "android.rebuild.applicationInfo"
     const val APP_INFO = "android.appInfo"

     const val PACKAGE_WHATSAPP = "com.whatsapp"

     fun clearAllNotifications(context: NotifierListener, notificationKey : String){
        if (DataManager(context).getOptionClearAllNotification()) {
            context.cancelNotification(notificationKey)
        }
    }

    fun validateNotificationIsWhatsapp(bundler : Bundle) : Boolean{
        var processName = (bundler[APPLICATION_INFO] as? ApplicationInfo)?.processName

        if (processName == null){
            processName = (bundler[APP_INFO] as? ApplicationInfo)?.processName
        }

       return processName?.toLowerCase(Locale.ROOT) == PACKAGE_WHATSAPP.toLowerCase(Locale.ROOT)
               && validateNotificationInfoIsNull(bundler)
    }

    fun ignoreNotificationOf(bundler : Bundle,name: String) : Boolean {
        val nameReceiver = bundler.getString(Notification.EXTRA_TITLE)
        val isEqual = nameReceiver == name
        return isEqual
    }



    // Ignore notifications when exist image or notifications group
    private fun validateNotificationInfoIsNull(bundler : Bundle) : Boolean{
        return bundler.getString(Notification.EXTRA_BIG_TEXT) == null &&
               bundler.getString(Notification.EXTRA_SUMMARY_TEXT) == null
    }


}