package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_progress -> {
                    // Navigate to the Progress Activity
                    val intent = Intent(this, ProgressActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
