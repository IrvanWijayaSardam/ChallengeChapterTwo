package com.irvanw.challengechaptertwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class activity_thanks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanks)
        val tvThanks = findViewById<TextView>(R.id.tvThanks)

        val total = intent.getStringExtra("total")

        Toast.makeText(this, "${total}", Toast.LENGTH_SHORT).show()
        tvThanks.setText("Rp. " + total)
    }
}