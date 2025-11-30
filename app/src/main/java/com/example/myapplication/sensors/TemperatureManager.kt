package com.example.myapplication.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class TemperatureManager(context: Context):
        SensorEventListener {
            private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            private val temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

            private var onTemperatureChanged: ((Float) -> Unit)? = null
            private var onError: ((String) -> Unit)? = null
            private var isSensorAvailable = false

            //listen to temp sensor
            fun startListening(onChanged: (Float) -> Unit, onError: (String) -> Unit){
                this.onTemperatureChanged = onChanged
                this.onError = onError

                if(temperatureSensor != null){
                    sensorManager.registerListener(this, temperatureSensor,SensorManager.SENSOR_DELAY_NORMAL)
                } else {
                    isSensorAvailable = false
                    onError("Temperature sensor not available")
                }
            }

            //stop listen
            fun stopListening(){
                sensorManager.unregisterListener(this)
            }
            //handle sensor events
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_AMBIENT_TEMPERATURE){
                    val temperature = event.values[0]

                    if (temperature in -50f..80f){
                        onTemperatureChanged?.invoke(temperature)
                    } else {
                        onError?.invoke("Invalid temperature reading: $temperature°C")
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int){
                //handle accuracy changes

            }




        }