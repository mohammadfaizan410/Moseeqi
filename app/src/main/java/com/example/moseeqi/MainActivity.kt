package com.example.moseeqi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserWithPlaylists
import com.example.moseeqi.auth.LoginActivity
import com.example.moseeqi.ayesha.AppSettingsActivity
import com.example.moseeqi.constants.Constants
import com.example.moseeqi.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val userDB: UserRoomDatabase by lazy {
            Room.databaseBuilder(this, UserRoomDatabase::class.java, "PlayListDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        setContentView(binding.root)
//        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
//
//        if(sharedPreferences.getString(Constants.USERNAME_KEY,"none") == "none"){
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }else {
//            var allUsers = userDB.playListDao().getUsersWithPlaylists();
//            allUsers.forEach {
//                if(it.user.username ==  sharedPreferences.getString(Constants.USERNAME_KEY,"none")){
//                    println("user is already logged in");
//                }
//            }
//        }
//
//        binding.login.setOnClickListener {
//            binding.login.setOnClickListener {
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {

            try {
                when (it.itemId) {
                    R.id.homeNavbtn -> {

                        true
                    }
                    R.id.settingsNavbtn -> {
                        val intent = Intent(this, AppSettingsActivity::class.java)
                        startActivity(intent)
                        true
                    } else -> {
                    true
                }
                }
            } catch (e : Exception) {
                throw e
            }
        }
    }



}