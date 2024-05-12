package com.example.moseeqi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moseeqi.databinding.ActivityHomePageBinding
import com.example.moseeqi.databinding.ActivityHomePageBinding.inflate

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLikedSongs.setOnClickListener {
            startActivity(Intent(this, LikedSongsActivity::class.java))
        }

        binding.btnRecentSongs.setOnClickListener {
            startActivity(Intent(this, RecentSongsActivity::class.java))
        }
    }
}
