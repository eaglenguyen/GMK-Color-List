package com.egor.gmk.ui


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.egor.gmk.R
import com.egor.gmk.databinding.ActivityDetailBinding



class MainActivity : AppCompatActivity() {

    // variables for nav controller and viewmodel
    private lateinit var navController: NavController

    private lateinit var binding: ActivityDetailBinding




    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = (binding.root)
        setContentView(view)


        // A reference to nav host fragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment



        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController




        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)


        // Logic for home button to navigate to colorList
        binding.bottomNav.setOnItemSelectedListener {

            // Logic for Implicit intent ( to URL web page )
            val titles : TextView = findViewById(R.id.titles)
            val queryUrl: Uri = Uri.parse("${ColorListFragment.SEARCH_PREFIX}${titles.text}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)


        // Logic for home button and info button
            when (it.itemId) {
                R.id.home -> findNavController(R.id.nav_host_fragment).navigate(
                    R.id.colorListFragment
                )
                R.id.settings -> this.startActivity(intent)

            }
            true
        }

        // Logic to only show bottom nav on detailed keycap fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.greenFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.blueFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.blackFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.whiteFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.redFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.yellowFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.pinkFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.purpleFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.greyFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.brownFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.orangeFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.multiColorFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.additionFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE
            }

            }


        }


    // Logic for back arrow to previous fragment
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}











