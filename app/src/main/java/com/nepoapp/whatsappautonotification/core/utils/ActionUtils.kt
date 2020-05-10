package com.nepoapp.whatsappautonotification.core.utils

import android.app.Notification
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat

object ActionUtils {

    fun getActionReply(notification : Notification) : NotificationCompat.Action?{
        val action = NotificationCompat.getAction(notification,0)
        return action ?: null
    }

    fun sendMessageWhatsapp(context: Context,message: String,action: NotificationCompat.Action){
        val intent = Intent()
        val bundler = Bundle()
        val remoteInput = arrayListOf<RemoteInput>()

        for (input in action?.remoteInputs){
            bundler.putCharSequence(input.resultKey, message)

            val buffer = RemoteInput
                .Builder(input.resultKey)
                .setLabel(input.label)
                .setChoices(input.choices)
                .setAllowFreeFormInput(input.allowFreeFormInput)
                .addExtras(input.extras)
                .build()

            remoteInput.add(buffer)
        }

        RemoteInput.addResultsToIntent(remoteInput.toTypedArray(),intent,bundler)
        action.actionIntent.send(context,124578,intent)
    }
}