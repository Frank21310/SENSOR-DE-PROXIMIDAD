package com.example.proximitysensor2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edtTitulo: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTitulo = findViewById(R.id.txtTitulo)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (sensor != null) {
            sensorManager.registerListener(
                proximitySensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        } else {
            edtTitulo.setText("No existe sensor de proximidad")
        }
    }

    private var proximitySensorEventListener: SensorEventListener? = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // method to check accuracy changed in sensor.
        }

        override fun onSensorChanged(event: SensorEvent) {
            var window : Window
            window=getWindow()
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    edtTitulo.text = "Objeto cerca del sensor"
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor("#546e7a")))

                } else {
                    edtTitulo.text = "Objeto lejos del sensor"
                    window.setBackgroundDrawable(ColorDrawable(Color.parseColor("#494949")))
                }
            }
        }
    }
}