package com.aditprayogo.mybroadcastreceiver

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class DownloadService : IntentService("DownloadService") {

    companion object {
        val TAG = DownloadService::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {

        Log.d(TAG, "Download service di jalankan")

        if (intent != null) {
            try {
                Thread.sleep(5000)

            }catch (e: InterruptedException) {
                e.printStackTrace()
            }

            //send broadcast ke main activity
            val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
            sendBroadcast(notifyFinishIntent)
        }
    }


}
