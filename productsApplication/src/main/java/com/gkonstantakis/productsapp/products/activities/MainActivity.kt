package com.gkonstantakis.productsapp.products.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.gkonstantakis.productsapp.products.R
import com.gkonstantakis.productsapp.products.databinding.ActivityMainBinding

class MainActivity : androidx.fragment.app.FragmentActivity() {

    private lateinit var activityViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityViewBinding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        navigateToProductsScreen()
    }

    fun navigateToProductsScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentByTag("fragment_nav") as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.productsFragment
        navHostFragment.navController.graph = graph
    }
}