package com.alexcao.study.hocandroidne

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val textView:TextView = findViewById<TextView>(R.id.data_text_view)
        val data = intent.getStringExtra(MAIN_ACTIVITY_DATA)
        textView.text = data
    }
}