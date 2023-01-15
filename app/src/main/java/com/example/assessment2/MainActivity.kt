package com.example.assessment2

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.main_background
import kotlinx.android.synthetic.main.activity_main.search_button
import kotlinx.android.synthetic.main.activity_main.videoview
import kotlinx.android.synthetic.main.activity_main.videoview_cover

class MainActivity : AppCompatActivity() {

    //would have opted for a JSON file but had limited time
    public var database = mutableMapOf<Any, Any>(
        "Al Reem Island" to mutableMapOf<Any, Any>("dir" to "alreem", "data" to mutableMapOf<Any, Any>(
            "Najmat C1 Tower" to mutableMapOf<Any, Any>("dir" to "najmatc1", "data" to mutableMapOf<Any, Any>(
                "Najmat C1 1 Bedroom" to mutableMapOf<Any, Any>("dir" to "najmatc1br1",
                    "size" to 882,
                    "price" to 60000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_najmatc1_najmatc1br1_1,
                        R.drawable.alreem_najmatc1_najmatc1br1_2,
                        R.drawable.alreem_najmatc1_najmatc1br1_3,
                        R.drawable.alreem_najmatc1_najmatc1br1_4,
                        R.drawable.alreem_najmatc1_najmatc1br1_5,
                        R.drawable.alreem_najmatc1_najmatc1br1_6
                    )
                ),
                "Najmat C1 2 Bedroom" to mutableMapOf<Any, Any>("dir" to "najmatc1br2",
                    "size" to 1300,
                    "price" to 80000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_najmatc1_najmatc1br1_1,
                        R.drawable.alreem_najmatc1_najmatc1br1_2,
                        R.drawable.alreem_najmatc1_najmatc1br1_3,
                        R.drawable.alreem_najmatc1_najmatc1br1_4,
                        R.drawable.alreem_najmatc1_najmatc1br1_5,
                        R.drawable.alreem_najmatc1_najmatc1br1_6
                    )
                ),
                "Najmat C1 3 Bedroom" to mutableMapOf<Any, Any>("dir" to "najmatc1br3",
                    "size" to 1883,
                    "price" to 165000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_najmatc1_najmatc1br1_1,
                        R.drawable.alreem_najmatc1_najmatc1br1_2,
                        R.drawable.alreem_najmatc1_najmatc1br1_3,
                        R.drawable.alreem_najmatc1_najmatc1br1_4,
                        R.drawable.alreem_najmatc1_najmatc1br1_5,
                        R.drawable.alreem_najmatc1_najmatc1br1_6
                    )
                )
            )), //tower
            "Al Qurm View" to mutableMapOf<Any, Any>("dir" to "alqurm", "data" to mutableMapOf<Any, Any>(
                "Al Qurm 1 Bedroom" to mutableMapOf<Any, Any>("dir" to "alqurm1br",
                    "size" to 780,
                    "price" to 75000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_alqurm_alqurm1br_1,
                        R.drawable.alreem_alqurm_alqurm1br_2,
                        R.drawable.alreem_alqurm_alqurm1br_3,
                        R.drawable.alreem_alqurm_alqurm1br_4,
                        R.drawable.alreem_alqurm_alqurm1br_5
                    )
                ),
                "Al Qurm 5 Bedroom Penthouse" to mutableMapOf<Any, Any>("dir" to "alqurm5br",
                    "size" to 4484,
                    "price" to 250000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_alqurm_alqurm5br_1,
                        R.drawable.alreem_alqurm_alqurm5br_2,
                        R.drawable.alreem_alqurm_alqurm5br_3,
                        R.drawable.alreem_alqurm_alqurm5br_4,
                        R.drawable.alreem_alqurm_alqurm5br_5,
                        R.drawable.alreem_alqurm_alqurm5br_6,
                        R.drawable.alreem_alqurm_alqurm5br_7,
                        R.drawable.alreem_alqurm_alqurm5br_8,
                        R.drawable.alreem_alqurm_alqurm5br_9,
                        R.drawable.alreem_alqurm_alqurm5br_10,
                        R.drawable.alreem_alqurm_alqurm5br_11
                    )
                )
            )), //tower
            "Nalaya Villas" to mutableMapOf<Any, Any>("dir" to "nalaya", "data" to mutableMapOf<Any, Any>(
                "Nalaya 3 Bedroom" to mutableMapOf<Any, Any>("dir" to "nalayabr3",
                    "size" to 3700,
                    "price" to 220000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_nalaya_nalayabr3_1,
                        R.drawable.alreem_nalaya_nalayabr3_2,
                        R.drawable.alreem_nalaya_nalayabr3_3,
                        R.drawable.alreem_nalaya_nalayabr3_4,
                        R.drawable.alreem_nalaya_nalayabr3_5,
                        R.drawable.alreem_nalaya_nalayabr3_6,
                        R.drawable.alreem_nalaya_nalayabr3_7
                    )
                ),
                "Nalaya 4 Bedroom" to mutableMapOf<Any, Any>("dir" to "nalayabr4",
                    "size" to 4500,
                    "price" to 260000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_nalaya_nalaya4br_1,
                        R.drawable.alreem_nalaya_nalaya4br_2,
                        R.drawable.alreem_nalaya_nalaya4br_3,
                        R.drawable.alreem_nalaya_nalaya4br_4,
                        R.drawable.alreem_nalaya_nalaya4br_5
                    )
                ),
                "Nalaya 5 Bedroom" to mutableMapOf<Any, Any>("dir" to "nalayabr5",
                    "size" to 5500,
                    "price" to 360000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.alreem_nalaya_nalaya5br_1,
                        R.drawable.alreem_nalaya_nalaya5br_2,
                        R.drawable.alreem_nalaya_nalaya5br_3,
                        R.drawable.alreem_nalaya_nalaya5br_4,
                        R.drawable.alreem_nalaya_nalaya5br_5,
                        R.drawable.alreem_nalaya_nalaya5br_6,
                        R.drawable.alreem_nalaya_nalaya5br_7,
                        R.drawable.alreem_nalaya_nalaya5br_8,
                        R.drawable.alreem_nalaya_nalaya5br_9,
                        R.drawable.alreem_nalaya_nalaya5br_10,
                        R.drawable.alreem_nalaya_nalaya5br_11,
                        R.drawable.alreem_nalaya_nalaya5br_12
                    )
                )
            )), //tower
        )), //region
        "Abu Dhabi" to mutableMapOf<Any, Any>("dir" to "abudhabi", "data" to mutableMapOf<Any, Any>(
            "Emerald Tower" to mutableMapOf<Any, Any>("dir" to "emeraldtower", "data" to mutableMapOf<Any, Any>(
                "Emerald 4 Bedroom" to mutableMapOf<Any, Any>("dir" to "emerald4br",
                    "size" to 2600,
                    "price" to 115000,
                    "balcony" to true,
                    "parking" to false,
                    "pool" to false,
                    "gym" to false,
                    "images" to arrayOf(
                        R.drawable.abudhabi_emeraldtower_emerald4br_1,
                        R.drawable.abudhabi_emeraldtower_emerald4br_2,
                        R.drawable.abudhabi_emeraldtower_emerald4br_3,
                        R.drawable.abudhabi_emeraldtower_emerald4br_4,
                        R.drawable.abudhabi_emeraldtower_emerald4br_5
                    )
                ),
                "Emerald Office M01" to mutableMapOf<Any, Any>("dir" to "emeraldofficem01",
                    "size" to 3600,
                    "price" to 183000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.abudhabi_emeraldtower_officem01_1,
                        R.drawable.abudhabi_emeraldtower_officem01_2,
                        R.drawable.abudhabi_emeraldtower_officem01_3,
                        R.drawable.abudhabi_emeraldtower_officem01_4,
                        R.drawable.abudhabi_emeraldtower_officem01_5
                    )
                )
        )), //tower
        "Lake View Tower" to mutableMapOf<Any, Any>("dir" to "lakeviewtower", "data" to mutableMapOf<Any, Any>(
            "Lake View 3 Bedroom Sea View" to mutableMapOf<Any, Any>("dir" to "lakeview3brview",
                "size" to 3200,
                "price" to 105000,
                "balcony" to true,
                "parking" to false,
                "pool" to false,
                "gym" to false,
                "images" to arrayOf(
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_1,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_2,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_3,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_4,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_5,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_6,
                    R.drawable.abudhabi_lakeviewtower_lakeview3brview_7
                )
            ),
            "Lake View 3 Bedroom" to mutableMapOf<Any, Any>("dir" to "lakeview3br",
                "size" to 3200,
                "price" to 90000,
                "balcony" to true,
                "parking" to false,
                "pool" to false,
                "gym" to false,
                "images" to arrayOf(
                    R.drawable.abudhabi_lakeviewtower_lakeview3br_1,
                    R.drawable.abudhabi_lakeviewtower_lakeview3br_2,
                    R.drawable.abudhabi_lakeviewtower_lakeview3br_3
                )
            )
        )), //tower
        "Wave Tower Corniche" to mutableMapOf<Any, Any>("dir" to "wavetowercorniche", "data" to mutableMapOf<Any, Any>(
            "Wave Tower 4 Bedroom" to mutableMapOf<Any, Any>(
                    "dir" to "wavetower4br",
                    "size" to 3300,
                    "price" to 200000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_1,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_2,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_3,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_4,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_5,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_6,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_7,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_8,
                        R.drawable.abudhabi_wavetowercorniche_wavetower4br_9
                    )
                ),
                "Wave Tower 5 Bedroom" to mutableMapOf<Any, Any>(
                    "dir" to "wavetower5br",
                    "size" to 3800,
                    "price" to 260000,
                    "balcony" to true,
                    "parking" to true,
                    "pool" to true,
                    "gym" to true,
                    "images" to arrayOf(
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_1,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_2,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_3,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_4,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_5,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_6,
                        R.drawable.abudhabi_wavetowercorniche_wavetower5br_7
                    )
                )
            )) //tower
        )) //region
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //Set Video
        setBackground()

        search_button.setOnClickListener { startActivity(Intent(this, Search::class.java)) }
        search_bar.setOnClickListener { startActivity(Intent(this, Search::class.java)) }
        settings_button.setOnClickListener { startActivity(Intent(this, Settings::class.java)) }

        main_background.post { popupView() }
    }

    fun popupView()
    {
        if (getPreferenceValue("popup_enabled") == "no") return
        //inflater
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popup = inflater.inflate(R.layout.popup_window, null)

        //create window
        val len = LinearLayout.LayoutParams.MATCH_PARENT
        val window = PopupWindow(popup, len, len, true)

        //show popup
        window.showAtLocation(main_background, Gravity.CENTER, 0, 0)

        val message = "Welcome to Bramwell Real Estate\n\nThis app is for you to be able to search the differerent available real estate options in Abu Dhabi.\n\nTo get started, click on the Search icon.\n\nTo dismiss this popup, click me again.\n(Disable me in Settings)"
        popup.findViewById<TextView>(R.id.popup_text).text = message

        //listener for when popup is clicked
        popup.setOnClickListener { window.dismiss() }
    }

    //BACKGROUND THINGS
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

    //sets background
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
        search_bar.background = ResourcesCompat.getDrawable(resources, button_background, null)
        contact_text.setTextColor(text)
        moto_text.setTextColor(text)

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