package com.nepoapp.whatsappautonotification.core.manager

import android.content.Context

class DataManager(private val context : Context) {

    private val shared = context.getSharedPreferences(
        ManagerKeyInfo.NAME_ORIGEM,
        ManagerKeyInfo.MOD_PRIVATE)

    fun getEnabledAccessNotification() : Boolean =
        shared.getBoolean(ManagerKeyInfo.ENABLED_ACCESS_NOTIFICATION,false)

    fun setEnabledAccessNotification(enabled : Boolean) =
        shared.edit().putBoolean(ManagerKeyInfo.ENABLED_ACCESS_NOTIFICATION,enabled)
            .commit()

    fun getOptionClearAllNotification() : Boolean =
        shared.getBoolean(ManagerKeyInfo.OPTION_CLEAR_ALL_NOTIFICATIONS,true)

    fun setOptionClearAllNotification(enabled: Boolean) =
        shared.edit().putBoolean(ManagerKeyInfo.OPTION_CLEAR_ALL_NOTIFICATIONS,enabled)
            .commit()

    fun getOptionEnabledModOffline() : Boolean =
        shared.getBoolean(ManagerKeyInfo.OPTION_ENABLED_MOD_OFFLINE,false)

    fun setOptionEnabledModOffline(enabled: Boolean) =
        shared.edit().putBoolean(ManagerKeyInfo.OPTION_ENABLED_MOD_OFFLINE,enabled)
            .commit()

}