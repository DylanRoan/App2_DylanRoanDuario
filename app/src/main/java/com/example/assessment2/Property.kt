package com.example.assessment2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.activity_property.home_button
import kotlinx.android.synthetic.main.activity_property.main_background
import kotlinx.android.synthetic.main.activity_property.search_button
import kotlinx.android.synthetic.main.activity_property.settings_button
import kotlinx.android.synthetic.main.activity_property.videoview
import kotlinx.android.synthetic.main.activity_property.videoview_cover
import java.lang.Math.round

class Property : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_property)

        //set video
        setBackground()

        //buttons
        home_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        search_button.setOnClickListener { startActivity(Intent(this, Search::class.java)) }
        settings_button.setOnClickListener { startActivity(Intent(this, Settings::class.java)) }

        //set text
        setText()
        //picture slideshow
        setPictures()
    }

    //sets pictures
    var pictures = arrayOf<Int>()
    fun setPictures()
    {
        var tag = intent.getStringExtra("tag") as String
        var tags = tag.split("|")
        var data = Search().getItem(tags[0], tags[1], tags[2])

        pictures = data["images"] as Array<Int>

        left_scroll.setOnClickListener { slideshow(-1) }
        right_scroll.setOnClickListener { slideshow(1) }
        slideshow()
        property_image.setOnClickListener { popupView() }
    }
    //handles image popup
    fun popupView()
    {
        //inflater
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popup = inflater.inflate(R.layout.popup_window, null)

        //create window
        val len = LinearLayout.LayoutParams.MATCH_PARENT
        val window = PopupWindow(popup, len, len, true)

        //show popup
        window.showAtLocation(main_background, Gravity.CENTER, 0, 0)

        //set image
        var image = popup.findViewById<ImageView>(R.id.popup_image)
        val drawable = ResourcesCompat.getDrawable(resources, pictures[current], null)

        image.setImageDrawable(drawable)

        //listener for when popup is clicked
        popup.setOnClickListener { window.dismiss() }
    }

    //handles slideshow
    var current = 0
    fun slideshow(no : Int = 0)
    {
        current += no

        if (current == 0) left_scroll.visibility = View.INVISIBLE
        else left_scroll.visibility = View.VISIBLE
        if (current == pictures.size - 1) right_scroll.visibility = View.INVISIBLE
        else right_scroll.visibility = View.VISIBLE

        val drawable = ResourcesCompat.getDrawable(resources, pictures[current], null)
        property_image.setImageDrawable(drawable)
    }

    //sets property information
    fun setText()
    {
        var tag = intent.getStringExtra("tag") as String
        var tags = tag.split("|")
        var data = Search().getItem(tags[0], tags[1], tags[2])

        property_title.text = tags[2]

        var price = data["price"] as Int
        var size = data["size"] as Int

        var output = "Propery Details\n" +
                "Total Price: ${data["price"]} AED\n" +
                "Size: ${data["size"]} sqft.\n" +
                "Price/Sqft: ${round((price/size).toFloat())} AED/Sqft\n" +
                "Rent: ${round((price/12).toFloat())} AED/Month\n\n" +
                "Accomodations\n" +
                "Balcony: ${if (data["balcony"] as Boolean) "Yes" else "No"}\n" +
                "Parking Lot: ${if (data["parking"] as Boolean) "Yes" else "No"}\n" +
                "Swimming Pool: ${if (data["pool"] as Boolean) "Yes" else "No"}\n" +
                "Gym: ${if (data["gym"] as Boolean) "Yes" else "No"}"

        property_description.text = output
    }

    //BACKGROUND THINGS
    override fun onResume() {
        super.onResume()
        setBackground()
        setText()
        setPictures()
    }

    //gets saved preference
    fun getPreferenceValue(key : String): String {
        var pref = "appPrefences"
        val sp = getSharedPreferences(pref, 0)
        return sp.getString(key, "yes") as String
    }

    //sets background video
    fun setBackground()
    {
        //gets background colors
        val elements = Settings().getColours(getPreferenceValue("dark_mode"))
        val background = ResourcesCompat.getColor(resources, elements[0] as Int, null)
        var buttons = ResourcesCompat.getColor(resources, elements[1] as Int, null)
        val text = ResourcesCompat.getColor(resources, elements[2] as Int, null)
        val button_background = elements[3] as Int

        val arrow = if (getPreferenceValue("dark_mode").equals("yes")) R.drawable.leftarrow else R.drawable.leftarrow_black

        //set background
        property_title.setTextColor(text)
        property_description.setTextColor(text)
        call.setTextColor(text)

        left_scroll.setImageDrawable(ResourcesCompat.getDrawable(resources, arrow, null))
        right_scroll.setImageDrawable(ResourcesCompat.getDrawable(resources, arrow, null))
        main_background.setBackgroundColor(background)

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