package com.onlinekroy.onlinekroy.views.dashboard.seller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.onlinekroy.onlinekroy.R
import com.onlinekroy.onlinekroy.databinding.ActivitySellerDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerDashboard : AppCompatActivity() {
    private  lateinit var binding: ActivitySellerDashboardBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragmentContainerView1)
        val appBarConfig = AppBarConfiguration(setOf(
            R.id.myProductFragment,
            R.id.uploadProductFragment,
            R.id.sellerProfileFragment,
        ))

        binding.bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController,appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}