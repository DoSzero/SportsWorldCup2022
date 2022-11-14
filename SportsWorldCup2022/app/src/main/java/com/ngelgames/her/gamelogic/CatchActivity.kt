package com.ngelgames.her.gamelogic

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.ngelgames.her.R
import com.ngelgames.her.databinding.ActivityCatchBinding
import java.util.*

class CatchActivity : AppCompatActivity() {

    var score: Int = 0
    var imageArray = ArrayList<ImageView>()

    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable: Runnable = Runnable { }

    private lateinit var binding: ActivityCatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_catch)

        binding.catchFruits = this
        binding.score = getString(R.string.score_0)

        score = 0

        imageArray = arrayListOf(
            binding.ivItem1,
            binding.ivItem2,
            binding.ivItem3,
            binding.ivItem4,
            binding.ivItem5,
            binding.ivItem6,
            binding.ivItem7,
            binding.ivItem8,
            binding.ivItem9
        )

        hideImages()
        playAndRestart()
    }


    private fun hideImages() {
        runnable = Runnable {
            for (image in imageArray) {
                image.visibility = View.INVISIBLE
            }
            val index = Random().nextInt(8-0)
            imageArray[index].visibility = View.VISIBLE
            handler.postDelayed(runnable, 450)
        }
        handler.post(runnable)
    }

    @SuppressLint("SetTextI18n")
    fun increaseScore() {
        score++
        binding.score = "Score : $score"
    }

    @SuppressLint("SetTextI18n")
    fun playAndRestart() {
        score = 0
        binding.score = "Score : $score"
        hideImages()
        binding.time = "Time : " + 20000/1000

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }

        object:CountDownTimer(20000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {

                binding.time = "Time's up!!!"
                handler.removeCallbacks(runnable)

                val dialog = AlertDialog.Builder(this@CatchActivity).apply {
                    setCancelable(false)
                    setTitle("Catch the balls")
                    setMessage("Your score : $score\n Would you like play again?")
                }

                dialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
                    playAndRestart()
                }

                    .setNegativeButton(getString(R.string.no)) { _, _ ->
                    score = 0

                    ("Score : $score").apply {
                        binding.score = this
                    }

                    ("Time : " + "0").apply {
                        binding.time = this
                    }

                    for (image in imageArray) {
                        image.visibility = View.INVISIBLE
                    }

                    finish()
                }

                dialog.create().apply {
                    show()
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                binding.time = getString(R.string.time) + p0 / 1000
            }
        }.start()
    }
}
