package com.work.theIsle.jetpack.observer

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.work.supportlib.LoggerUtils

/**
 * @Author TIKOU
 * @Date 2022/7/17-19:03
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 */
class MyLocationObserver(private var context: Context) : LifecycleObserver {

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun startGetLocation() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PERMISSION_GRANTED
        ) {
            return
        }
        locationListener = MyLocationListener()
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 1000, 3F, locationListener
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun stopGetLocation() {
        locationManager.removeUpdates(locationListener)

    }

    class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            LoggerUtils.i("onLocationChanged $location")
        }
    }
}