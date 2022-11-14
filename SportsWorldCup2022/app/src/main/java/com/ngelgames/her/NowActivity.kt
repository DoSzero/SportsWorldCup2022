package com.ngelgames.her

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ngelgames.her.gamelogic.CatchActivity

class NowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now)

        val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
        val nameTest: String? = sharPref.getString(ApMain.C1, "null")
        val deepTest: String? = sharPref.getString(ApMain.D1, "null")

        if (nameTest!!.contains("tdb2")) {
            Log.d("zero_filter", "nameWeb")
            Intent(this, WWWeb::class.java).also { startActivity(it) }
            finish()
        } else if (deepTest!!.contains("tdb2")) {
            Log.d("zero_filter", "deepWeb")
            Intent(this, WWWeb::class.java).also { startActivity(it) }
            finish()
        } else {
            Log.d("zero_filter", "toGame")
            Intent(this, CatchActivity::class.java).also { startActivity(it) }
            finish()
        }

    }
}