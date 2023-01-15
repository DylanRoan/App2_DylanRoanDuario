package com.example.assessment2

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.search_bar
import kotlinx.android.synthetic.main.activity_search.videoview
import java.net.URI


class Search : AppCompatActivity() {

    var matchingSave : MutableList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_search)

        //Set background
        setBackground()

        //listener for search bar changes
        search_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addSearchItems(searchItems(p0.toString()))
            }

        })

        //listener for the home and settings icon
        home_button.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        settings_button.setOnClickListener { startActivity(Intent(this, Settings::class.java)) }

        //wait until post
        main_background.post {
            //get initial matches
            var matching = searchItems("")
            addSearchItems(matching)
        }
    }


    //Search for matching items based on query and return matching list
    fun searchItems(input : String) : MutableList<String>
    {
        val database = MainActivity().database
        var matching : MutableList<String> = mutableListOf()

        for (region in database.keys)
        {
            region as String

            var towers = database[region] as MutableMap<*, *>
            towers = towers["data"] as MutableMap<*, *>

            for (tower in towers.keys)
            {
                tower as String

                var apartments = towers[tower] as MutableMap<*, *>
                apartments = apartments["data"] as MutableMap<*, *>

                for (apartment in apartments.keys)
                {
                    apartment as String

                    var tag = "$region|$tower|$apartment"
                    if (tag.lowercase().contains(input.lowercase())) {
                        if (matching.size < 20)
                        {
                            matching = matching.plus(tag) as MutableList<String>
                        }
                    }
                }
            }
        }
        return matching
    }

    //Get property data based on given region, building, and apartment
    fun getItem(region : String, tower : String, apartment : String) : MutableMap<*, *>
    {
        var data = MainActivity().database as MutableMap<*, *>

        data = data[region] as MutableMap<*, *>
        data = data["data"] as MutableMap<*, *>

        data = data[tower] as MutableMap<*, *>
        data = data["data"] as MutableMap<*, *>

        return data[apartment] as MutableMap<*, *>
    }

    //Add search items to searchlist
    fun addSearchItems(matching : MutableList<String>)
    {
        //gets background colors
        val elements = Settings().getColours(getPreferenceValue("dark_mode"))
        val button_background = elements[3] as Int

        scroll_container.removeAllViews()
        for (match in matching)
        {
            var split = match.split("|")

            var inflate = layoutInflater.inflate(R.layout.scrollitem_property, null)

            var backr = inflate.findViewById<ConstraintLayout>(R.id.scroll_background)
            backr.background = ResourcesCompat.getDrawable(resources, button_background, null)

            inflate.findViewById<TextView>(R.id.scrollitem_title).text = split[2]

            var data = getItem(split[0], split[1], split[2])

            var desc = inflate.findViewById<TextView>(R.id.scrollitem_description)
            "${split[0]} ${split[1]}\n${data["price"]} AED\n${data["size"]} sqft".also { desc.text = it }

            val images = data["images"] as Array<*>
            val imageInt = images[0] as Int
            val drawable = ResourcesCompat.getDrawable(resources, imageInt, null)
            inflate.findViewById<ImageView>(R.id.scrollitem_image).setImageDrawable(drawable)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 25, 0, 0)
            inflate.layoutParams = layoutParams

            inflate.tag = match

            inflate.setOnClickListener { goToPage(it.tag as String) }

            scroll_container.addView(inflate)
        }
    }

    fun goToPage(tag : String)
    {
        var intent = Intent(this, Property::class.java)
        intent.putExtra("tag", tag)
        startActivity(intent)
        addSearchItems(searchItems(""))
    }

    //BACKGROUND THINGS
    override fun onResume() {
        super.onResume()
        setBackground()
        addSearchItems(matchingSave)
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

        //set background elements
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

