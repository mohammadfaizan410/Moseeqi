package com.example.moseeqi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moseeqi.databinding.ActivityRecentSongsBinding

class RecentSongsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentSongsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentSongsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Placeholder data for recent songs
        val recentSongs = listOf("Recent Song 1", "Recent Song 2", "Recent Song 3", "Recent Song 4", "Recent Song 5")

        // Setting up the RecyclerView
        binding.recyclerViewRecentSongs.apply {
            layoutManager = LinearLayoutManager(this@RecentSongsActivity)
            adapter = SimpleTextAdapter(recentSongs)  // Reusing SimpleTextAdapter from LikedSongsActivity
        }
    }
}
