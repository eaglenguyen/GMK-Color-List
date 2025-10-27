package com.egor.gmk.ui


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.egor.gmk.R
import com.egor.gmk.databinding.ActivityDetailBinding
import com.egor.gmk.ui.colorlist.ColorListFragment
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // variables for nav controller and viewmodel
    private lateinit var navController: NavController

    private lateinit var binding: ActivityDetailBinding


    // Change background color via activity_detail & fragment_gmk_list xml

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // Enable edge-to-edge layout
        WindowCompat.setDecorFitsSystemWindows(window, false) // optional for Android 15+



        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sets status bar to black color
        val controller = window.insetsController

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            // For Android 15+ (API 35 and up)
            controller?.setSystemBarsAppearance(
                0, // remove any light mode flags (so icons are light)
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )

            // Set status bar background color using new API
            window.setStatusBarContrastEnforced(false)

            // Use WindowInsets to change bar appearance
            val view = window.decorView
            view.setOnApplyWindowInsetsListener { v, insets ->
                v.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))
                insets
            }
        } else {
            // Fallback for Android 14 and below
            @Suppress("DEPRECATION")
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)

            WindowInsetsControllerCompat(window, window.decorView)
                .isAppearanceLightStatusBars = false
        }



        setSupportActionBar(binding.topAppBar)




        // A reference to nav host fragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController


        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)

        // Apply top inset to content below topbar
        ViewCompat.setOnApplyWindowInsetsListener(binding.navHostFragment) { toolbar, insets ->
            val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val layoutParams = toolbar.layoutParams as ConstraintLayout.LayoutParams

            toolbar.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = 175
                bottomMargin = 50
            }


            insets
        }


        // Apply top inset to topbar
        ViewCompat.setOnApplyWindowInsetsListener(binding.topAppBar) { toolbar, insets ->
            val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val layoutParams = toolbar.layoutParams as ConstraintLayout.LayoutParams

            layoutParams.topMargin = statusBars.top

            insets
        }




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
                R.id.keycapsDetailFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE
            }
            }


        }


    // Logic for back arrow to previous fragment
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}











