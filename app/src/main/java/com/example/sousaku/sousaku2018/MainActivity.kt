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


    var c:Int=0
    var flag:Int=0
    var acflag:Int=0
    var orflag:Int=0
    var orflag2:Int=0
    var updown:Double=0.0
    override fun onSensorChanged(event: SensorEvent?) {
        if(event==null) return
        if(event.sensor.type==Sensor.TYPE_ORIENTATION) {
            if(event.values[1]>=-15.0){  //ピッチ判定
                orflag=1
            }else{
                orflag=0
            }
            if(event.values[1]-updown>0.0){ //振り上げカウント禁止(現在のピッチ-直前のピッチで判定)
                orflag2=1
            }else{
                orflag2=0
            }
            updown=event.values[1].toDouble()
        }
        if(event.sensor.type==Sensor.TYPE_ACCELEROMETER){
            if(event.values[2]>30.0){ //加速度で鳴らすか判定
                acflag=1
            }
            if(event.values[2]<10.0){ //チャタリング的な奴の防止策(役に立ってるかはわからない)
                acflag=0
            }
            if(orflag==1 && flag==0 && acflag==1 && orflag2==1){ //全条件一致で鳴らしましょってところのところ
                c++
                flag=1
            }
            if(event.values[2]<10.0 && flag==1){ //鳴らし終わって加速度が戻ったら解除しましょってところのところのところ
                flag=0
            }
        }

        textView.text=c.toString() //カウント表示

    }

    override fun onResume() {
        super.onResume()
        val sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val oriSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        val accSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,oriSensor,SensorManager.SENSOR_DELAY_GAME)
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