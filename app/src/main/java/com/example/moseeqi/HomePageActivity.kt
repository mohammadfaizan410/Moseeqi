package com.example.moseeqi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moseeqi.constants.Constants
import com.example.moseeqi.databinding.ActivityHomePageBinding
import com.example.moseeqi.databinding.ActivityHomePageBinding.inflate
import java.util.prefs.Preferences

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)


        binding.btnLikedSongs.setOnClickListener {
            startActivity(Intent(this, LikedSongsActivity::class.java))
        }

        binding.btnRecentSongs.setOnClickListener {
            startActivity(Intent(this, RecentSongsActivity::class.java))
        }
        binding.logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit();
            editor.putString(Constants.USERNAME_KEY, "")
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
