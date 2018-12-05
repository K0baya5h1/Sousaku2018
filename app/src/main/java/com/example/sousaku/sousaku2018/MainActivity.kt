package com.example.sousaku.sousaku2018

import android.content.Context
import android.hardware.*
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,SensorEventListener,LocationListener{
    override fun onLocationChanged(location: Location?) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }



    override fun onSensorChanged(event: SensorEvent?) {
        /*var phi:Double
        var q:Double=0.0

            q=event.values[1].toDouble()
            textView.text="${q.toString()}"
            phi=Math.atan(q.toDouble())
            textView2.text=(phi.toString())
            //地磁気やってみたった
*/
        if(event==null) return
        textView.text=("角度:"+event.values[0].toInt().toString())
        textView2.text=("ピッチ:"+event.values[1].toInt().toString())
        textView3.text=("ロール:"+event.values[2].toInt().toString())



    }

    override fun onResume() {
        super.onResume()
        val sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        val sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}