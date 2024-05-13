package com.example.moseeqi.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.Playlist
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserDao
import com.ctis487.roomdatabasewithonetomanyrelation.db.PlaylistWithSongs
import com.ctis487.roomdatabasewithonetomanyrelation.db.Song
import com.ctis487.roomdatabasewithonetomanyrelation.db.User
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.example.moseeqi.constants.Constants
import com.example.moseeqi.databinding.RegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterBinding
    private val userDB: UserRoomDatabase by lazy {
        Room.databaseBuilder(this, UserRoomDatabase::class.java, "PlayListDB.db")
            .allowMainThreadQueries()  // Note: It's generally not recommended to allow main thread queries in production apps
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regitserBtn.setOnClickListener {
            val username = binding.registerUsername.text.toString()
            val password = binding.registerPass.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val existingUser = userDB.userDao().getUserByUsername(username)

            if (existingUser != null) {
                Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Creating a new user
            // Create a new user
            val userId = userDB.userDao().insertUser(User(username = username, password = password))

            // Create a playlist for the user
            val playlistId = userDB.playlistDao().insertPlaylist(Playlist(ownerId = userId, name = "Liked Playlist"))
            val playlist2 = userDB.playlistDao().insertPlaylist(Playlist(ownerId = userId, name = "Recent Playlist"))
            val song1 = Song(title = "Song 1", streamURL = "url1", artworkURL = "url1", playlistId = playlistId)
            val song2 = Song(title = "Song 2", streamURL = "url2", artworkURL = "url2", playlistId = playlistId)
            val song3 = Song(title = "Song 1", streamURL = "url1", artworkURL = "url1", playlistId = playlist2)
            val song4 = Song(title = "Song 1", streamURL = "url1", artworkURL = "url1", playlistId = playlist2)
            userDB.songDao().insertSong(song1)
            userDB.songDao().insertSong(song2)
            userDB.songDao().insertSong(song3)
            userDB.songDao().insertSong(song4)
            Toast.makeText(this, "User registered successfully with a playlist and songs", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
