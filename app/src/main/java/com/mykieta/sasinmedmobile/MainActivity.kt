package com.mykieta.sasinmedmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setShowQueueButtonListener()
    }

    private fun setShowQueueButtonListener() {
        var showQueueButton = findViewById<Button>(R.id.show_queue)
        showQueueButton.setOnClickListener {
            startActivity(Intent(this, ShowQueueActivity::class.java))
        }
    }


}