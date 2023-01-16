package com.kodex.mapkityandex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.mapview.MapView

class MainActivity : AppCompatActivity() {

    lateinit var mapKit: MapKit
    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}