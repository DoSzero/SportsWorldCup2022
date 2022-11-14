package com.ngelgames.her

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.*

class ApMain: Application() {

    companion object {

        const val AF_DEV_KEY = "a7nUZhpBEqswKXkY2ZEX7W"
        const val jsoupCheck = "7m6t"
        const val ONESIGNAL_APP_ID = "69f3df18-4cd2-4625-8643-8172d69c314b"

        val linkFilterPart1 = "http://sportsworld"
        val linkFilterPart2 = "cup2022.xyz/go.php?to=1&"

        val linkAppsCheckPart1 = "http://sportsworld"
        val linkAppsCheckPart2 = "cup2022.xyz/apps.txt"

        val odone = "sub_id_1="

        var MAIN_ID: String? = ""
        var C1: String? = "c11"
        var D1: String? = "d11"
        var CH: String? = "check"
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = Advertising(context)
        val idInfo = advertisingInfo.getAdvertisingId()

        val prefs = getSharedPreferences("SP", MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putString(MAIN_ID, idInfo)
        editor.apply()
    }

}

    class Advertising (context: Context) {
        private val adInfo = AdvertisingIdClient(context.applicationContext)
        suspend fun getAdvertisingId(): String = withContext(Dispatchers.IO) {
            adInfo.start()
            val adIdInfo = adInfo.info
            Log.d("getAdvertisingId = ", adIdInfo.id.toString())
            adIdInfo.id
        }
    }