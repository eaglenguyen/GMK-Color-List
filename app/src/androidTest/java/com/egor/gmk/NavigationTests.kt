package com.egor.gmk


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.egor.gmk.ui.ColorListFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @Test
    fun navigate_to_words_nav_component() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Create a graphical FragmentScenario for the TitleScreen
        val startScenario = launchFragmentInContainer<ColorListFragment>(themeResId =
        com.egor.gmk.R.style.Theme_GMK
        )


        startScenario.onFragment { fragment ->

            // Set the graph on the TestNavHostController
            navController.setGraph(com.egor.gmk.R.navigation.nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(com.egor.gmk.R.id.button)).perform(click())
        assertEquals(navController.currentDestination?.id, com.egor.gmk.R.id.colorListFragment)

    }

}