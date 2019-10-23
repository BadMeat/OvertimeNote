package com.dolan.arif.overtimenote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dolan.arif.overtimenote.util.DatePickerFragment
import com.dolan.arif.overtimenote.view.MenuDateFragment

class MainActivity : AppCompatActivity(), DatePickerFragment.DatePickerListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onDialogSet(tag: String?, year: Int, month: Int, day: Int) {
        val fragment = supportFragmentManager.primaryNavigationFragment as NavHostFragment
        val menuAddFragment = fragment.childFragmentManager.primaryNavigationFragment
        if (menuAddFragment is MenuDateFragment) {
            menuAddFragment.onDialogSet(tag, year, month, day)
        }
    }
}
