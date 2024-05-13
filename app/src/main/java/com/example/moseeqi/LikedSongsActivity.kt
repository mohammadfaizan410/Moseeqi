package com.example.moseeqi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moseeqi.databinding.ActivityLikedSongsBinding

class LikedSongsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLikedSongsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLikedSongsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val likedSongs = listOf("Song 1", "Song 2", "Song 3", "Song 4", "Song 5")

        binding.recyclerViewLikedSongs.apply {
            layoutManager = LinearLayoutManager(this@LikedSongsActivity)
            adapter = SimpleTextAdapter(likedSongs)
        }
    }
}
