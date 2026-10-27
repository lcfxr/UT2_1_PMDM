package com.example.act2_ud1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "PMDM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Estado: onCreate - La app se está creando")

        // 1. CONECTAR XML CON KOTLIN
        val textoMensaje = findViewById<TextView>(R.id.mensaje)
        val botonAccion = findViewById<Button>(R.id.btnAccion)

        // 2. CAPTURAR EVENTO
        botonAccion.setOnClickListener {
            textoMensaje.text = getString(R.string.mensaje_actualizado)
            Log.d(TAG, "¡TODO EN ORDEN!")
        }
    }

    //CICLO DE VIDA

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "La app es visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "La app está en primer plano")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "La app va a segundo plano")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "La app ya no es visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "La app se va a cerrar por completo")
    }
}