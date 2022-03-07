package com.example.gmk

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gmk.adapter.ColorAdapter
import com.example.gmk.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)





        // val myDataSet = Colordata().loadAffirmations()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ColorAdapter(dataset = "" , this)

        // var dataArray = arrayOf<String>(java.lang.String.valueOf(R.array.))
        // dataArray = activity.getResources().getStringArray(R.array.)
        // context.resources.getStringArray(R.array.words)


    }
}