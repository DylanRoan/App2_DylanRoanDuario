package com.example.assessment2

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        //handles background video/colors
        setBackground()

        //sets timer for 5 seconds
        Timer().schedule(5000)
        {
            var intent = Intent(this@SplashScreen, MainActivity::class.java)
            intent.putExtra("popup", true)
            startActivity(intent)
        }
    }

    //BACKGROUND THINGS
    //Checks if user resumes app
    override fun onResume() {
        super.onResume()
        setBackground()
    }

    //gets saved preference
    fun getPreferenceValue(key : String): String {
        var pref = "appPrefences"
        val sp = getSharedPreferences(pref, 0)
        return sp.getString(key, "yes") as String
    }


    //sets background aspects
    fun setBackground()
    {
        //gets background colors
        val elements = Settings().getColours(getPreferenceValue("dark_mode"))
        val background = ResourcesCompat.getColor(resources, elements[0] as Int, null)
        var buttons = ResourcesCompat.getColor(resources, elements[1] as Int, null)
        val text = ResourcesCompat.getColor(resources, elements[2] as Int, null)
        val button_background = elements[3] as Int

        //handles elements
        loading.setTextColor(text)

        //handles video
        var coverColor = Color.parseColor("#80000000")

        var videoBackground = getPreferenceValue("video_background")

        if (videoBackground.equals("no")) {
            videoview_cover.setBackgroundColor(background)
            return
        }
        videoview_cover.setBackgroundColor(coverColor)

        var uri = Uri.parse("android.resource://"
                + packageName
                + "/"
                + R.raw.buildings)
        videoview.setVideoURI(uri)

        videoview.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0f, 0f)
            val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
            val screenRatio = videoview.width / videoview.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                videoview.scaleX = scaleX
            } else {
                videoview.scaleY = 1f / scaleX
            }
        }

        videoview.start()
    }
}