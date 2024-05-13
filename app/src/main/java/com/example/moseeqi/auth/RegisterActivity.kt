package com.example.moseeqi.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.Playlist
import com.ctis487.roomdatabasewithonetomanyrelation.db.User
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
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
                binding.validationRegister.text = "Please enter a username!"
                return@setOnClickListener
            } else if (password.isEmpty()) {
                binding.validationRegister.text = "Please enter a password!"
                return@setOnClickListener
            }

            val existingUser = userDB.userDao().getUserByUsername(username)

            if (existingUser != null) {
                binding.validationRegister.text = "User already exists!!"
                return@setOnClickListener
            }

            // Creating a new user
            val newUser = User(username = username, password = password)
            userDB.userDao().insertUser(newUser)
            val dbUser = userDB.userDao().getUserByUsername(username);
            val playlistCount = userDB.playlistDao().getAllPlaylists().count() + 1
            val playlist = Playlist(playlistCount.toLong(),"recents", dbUser!!.userId.toLong() )
            val playlist1 = Playlist(playlistCount.toLong() + 1,"liked", dbUser!!.userId.toLong() )
            userDB.playlistDao().insertPlaylist(playlist);
            userDB.playlistDao().insertPlaylist(playlist1);
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
