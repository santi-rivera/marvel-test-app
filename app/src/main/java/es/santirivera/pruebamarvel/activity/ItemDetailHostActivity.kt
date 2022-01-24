package es.santirivera.pruebamarvel.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import es.santirivera.pruebamarvel.R
import es.santirivera.pruebamarvel.databinding.ActivityItemDetailBinding

@AndroidEntryPoint
class ItemDetailHostActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimary
                )
            )
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}