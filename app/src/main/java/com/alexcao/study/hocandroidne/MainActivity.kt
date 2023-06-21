package com.alexcao.study.hocandroidne

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate")
        println("This is the callback and called when the activity is first created.")
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        val tapMeButton:Button = findViewById<Button>(R.id.button)
        tapMeButton.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        println("onStart")
        println("This is the callback and called when the activity is becoming visible to the user.")
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        println("onResume")
        println("This is the callback and called when the activity will start interacting with the user.")
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
        println("This is the callback and called when the system is about to start resuming a previous activity.")
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        println("onStop")
        println("This is the callback and called when the activity is no longer visible to the user.")
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        println("This is the callback and called before the activity is destroyed by the system.")
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
        println("This is the callback and called when the activity restarts after stopping it.")
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }
}