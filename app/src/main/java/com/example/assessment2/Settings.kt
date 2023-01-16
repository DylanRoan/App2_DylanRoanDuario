package com.example.assessment2

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.home_button
import kotlinx.android.synthetic.main.activity_settings.main_background
import kotlinx.android.synthetic.main.activity_settings.search_button
import kotlinx.android.synthetic.main.activity_settings.videoview
import kotlinx.android.synthetic.main.activity_settings.videoview_cover

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_settings)

        //set background
        setBackground()

        //sets the listeners for each preference
        settingYN("video_background", video_enabled_button, video_enabled_image)
        settingYN("dark_mode", dark_mode_button, dark_mode_image)
        settingYN("popup_enabled", popup_button, popup_image)

        //navigation
        home_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        search_button.setOnClickListener { startActivity(Intent(this, Search::class.java)) }
    }

    //handles each preference
    fun settingYN(key : String, button : LinearLayout, image : ImageView)
    {
        if (getPreferenceValue(key) == "none") writeToPreference(key, "yes")
        var v = getPreferenceValue(key)
        setCheckMark(image, v.equals("yes"))
        button.setOnClickListener {
            var v = getPreferenceValue(key)
            if (v.equals("yes")) writeToPreference(key, "no")
            else writeToPreference(key, "yes")

            setCheckMark(image, getPreferenceValue(key).equals("yes"))

            //set background
            setBackground()
        }
    }

    //handles the checkmark or x
    fun setCheckMark(image : ImageView, check : Boolean)
    {
        var yes = R.drawable.yes
        var no = R.drawable.no

        if (check) image.setImageDrawable(ResourcesCompat.getDrawable(resources, yes, null))
        else image.setImageDrawable(ResourcesCompat.getDrawable(resources, no, null))
    }

    //writes the preference
    fun writeToPreference(key : String, preference : String) {
        var pref = "appPrefences"

        val editor = getSharedPreferences(pref, 0).edit()
        editor.putString(key, preference)
        editor.commit()
    }

    //returns background colors
    fun getColours(mode : String) : Array<*>
    {
        var background = R.color.darkmode_background
        var buttons = R.color.darkmode_button
        var text = R.color.white
        var button_background = R.drawable.scrollitem_background

        if (mode.equals("no"))
        {
            background = R.color.lightmode_background
            buttons = R.color.lightmode_button
            text = R.color.black
            button_background = R.drawable.scrollitem_background_white
        }

        return arrayOf(background, buttons, text, button_background)
    }

    //BACKGROUND THINGS
    override fun onResume() {
        super.onResume()
        setBackground()
    }

    //get saved preference
    fun getPreferenceValue(key : String): String {
        var pref = "appPrefences"
        val sp = getSharedPreferences(pref, 0)
        return sp.getString(key, "yes") as String
    }

    //sets background
    fun setBackground()
    {
        //gets background colors
        val elements = getColours(getPreferenceValue("dark_mode"))
        val background = ResourcesCompat.getColor(resources, elements[0] as Int, null)
        var buttons = ResourcesCompat.getColor(resources, elements[1] as Int, null)
        val text = ResourcesCompat.getColor(resources, elements[2] as Int, null)
        val button_background = elements[3] as Int

        //set background
        main_background.setBackgroundColor(background)
        video_enabled_button.background = ResourcesCompat.getDrawable(resources, button_background, null)
        dark_mode_button.background = ResourcesCompat.getDrawable(resources, button_background, null)
        popup_button.background = ResourcesCompat.getDrawable(resources, button_background, null)

        //handles video
        var coverColor = Color.parseColor("#80000000")


        var videoBackground = getPreferenceValue("video_background")

        if (videoBackground.equals("no")) {
            coverColor = buttons
            videoview_cover.setBackgroundColor(coverColor)
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