package com.kodex.mapkityandex

import android.Manifest
import android.app.DownloadManager.Query
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.VisibleRegionUtils
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchOptions
import com.yandex.mapkit.search.Session
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error

class MainActivity : AppCompatActivity(),
    UserLocationObjectListener,
    Session.SearchListener,
    CameraListener {

    lateinit var mapKit: MapKit
    private lateinit var mapView: MapView
    private lateinit var buttonTraffic: Button
    private lateinit var locationMapKit: MapKit
    private lateinit var searchEdit: EditText
    private lateinit var searchManager: SearchManager
    private lateinit var searchSession: Session




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("0c548aac-9805-4442-b991-5b0ed966faf1")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        buttonTraffic = findViewById(R.id.buttonTraffic)

        requestLocationPermission()

        showCameraPosition()

        buttonTraffic.setOnClickListener {
            showTraffic()
        }

    }
    // Получаем разрешение
    private fun requestLocationPermission(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION), 0)
            return
        }

    }


        // Показ пробок
    private fun showTraffic(){
        val mapKit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()
        val probki = mapKit.createTrafficLayer(mapView.mapWindow)
        probki.isTrafficVisible = true

    }
    // Показ карты
    private fun showCameraPosition(){
        mapView.map.move(CameraPosition(Point(45.408509, 36.953241), 11.0f, 0.0f, 0.0f ),
        Animation(Animation.Type.SMOOTH, 1f), null)

    }
    // Показ
    private fun sumbitQuery(query:String){
        searchSession = searchManager
            .submit(query,VisibleRegionUtils.toPolygon(mapView.map.visibleRegion),
                SearchOptions(), this)
    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onObjectAdded(p0: UserLocationView) {
        TODO("Not yet implemented")
    }

    override fun onObjectRemoved(p0: UserLocationView) {
        TODO("Not yet implemented")
    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
        TODO("Not yet implemented")
    }

    override fun onSearchResponse(p0: Response) {
        TODO("Not yet implemented")
    }

    override fun onSearchError(p0: Error) {
        TODO("Not yet implemented")
    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        p3: Boolean,
    ) {
        TODO("Not yet implemented")
    }

}