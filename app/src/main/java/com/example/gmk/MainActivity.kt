package com.example.gmk


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gmk.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*


class MainActivity : AppCompatActivity() {

    // variables for nav controller and viewmodel
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()




    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen logic
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }


        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // A reference to nav host fragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment



        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController




        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)
        bottomNav.setupWithNavController(navController)


        // Logic for home button to navigate to colorList
        bottomNav.setOnItemSelectedListener {



            // Logic for Implicit intent
            val titles : TextView = findViewById(R.id.textView)
            val queryUrl: Uri = Uri.parse("${ColorListFragment.SEARCH_PREFIX}${titles.text}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)


        // Logic for home button to navigate to colorList
            when (it.itemId) {
                R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.colorListFragment)
                R.id.settings -> this.startActivity(intent)
            }
            true
        }

        // Logic to only show bottom nav on greenFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.greenFragment -> bottomNav.visibility = View.VISIBLE
                R.id.blueFragment ->  bottomNav.visibility = View.VISIBLE
                else -> bottomNav.visibility = View.GONE
            }

            }


        }
    // Logic for back arrow to previous fragment
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}











