package com.example.gmk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_start)

            button.setOnClickListener {
                Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }

        }
    }
