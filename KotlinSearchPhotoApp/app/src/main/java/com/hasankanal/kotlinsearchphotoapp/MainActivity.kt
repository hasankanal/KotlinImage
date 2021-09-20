package com.hasankanal.kotlinsearchphotoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.loginFragment -> bottomNavigationView.visibility = View.GONE
                R.id.registerFragment -> bottomNavigationView.visibility = View.GONE
                R.id.landingFragment -> bottomNavigationView.visibility = View.GONE
                R.id.capturePhotoFragment -> bottomNavigationView.visibility = View.GONE
                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        })



        //Access Key
        // NBOtxLhO9LZFoz12fT1yeZIkLVEIV7w68p2gGpDIarg

        //Secret Key
        //WjIK8hd2-QtI9Cs2iG3vZQU1xDOjlfNGGoigzmOnmMg
    }
}