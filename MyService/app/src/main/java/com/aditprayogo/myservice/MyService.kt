package com.aditprayogo.myservice

import android.app.Service
import android.content.Intent
import android.nfc.Tag
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.UnsupportedOperationException

class MyService : Service() {

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not Yet Implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.d(TAG, "Service di jalankan....")
        //corutine di jalanakn
        GlobalScope.launch {
            //nunggu 3 detik
            delay(3000)
            stopSelf()
            Log.d(TAG,"Service dihentikan")
        }

        return START_STICKY
    }

    override fun onDestroy() {        super.onDestroy()
        Log.d(TAG, "onDestroy:")
    }
}
