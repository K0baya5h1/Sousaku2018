package com.example.sousaku.sousaku2018

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,SensorEventListener{
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }



    override fun onSensorChanged(event: SensorEvent?) {
        var def:Double=0.0
        var phi:Double
        var q:Double=0.0
        if(event==null) return
        if(event.sensor.type==Sensor.TYPE_MAGNETIC_FIELD){
                button.setOnClickListener{
                def= event.values[1].toDouble()

            }
            q=event.values[1].toDouble()-def
            textView.text="${q.toString()}"
            phi=Math.atan(q.toDouble())
            textView2.text=(phi.toString())
            //地磁気やってみた
        }

    }

    override fun onResume() {
        super.onResume()
        val sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accSensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        sensorManager.registerListener(this,accSensor,SensorManager.SENSOR_DELAY_NORMAL)
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