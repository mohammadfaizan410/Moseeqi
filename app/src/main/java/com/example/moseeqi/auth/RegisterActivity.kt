package com.example.moseeqi.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.util.newStringBuilder
import com.ctis487.roomdatabasewithonetomanyrelation.db.Playlist
import com.ctis487.roomdatabasewithonetomanyrelation.db.User
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserWithPlaylists
import com.example.moseeqi.MainActivity
import com.example.moseeqi.databinding.LoginBinding
import com.example.moseeqi.databinding.RegisterBinding

class RegisterActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: RegisterBinding;
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val userDB: UserRoomDatabase by lazy {
            Room.databaseBuilder(this, UserRoomDatabase::class.java, "PlayListDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        binding.regitserBtn.setOnClickListener {
            if(binding.registerUsername.text.toString() == ""){
                binding.validationRegister.setText("please enter a username!");
                return@setOnClickListener
            }else if(binding.registerPass.text.toString() == ""){
                binding.validationRegister.setText("Please enter a password");
                return@setOnClickListener
            }
            else{
                val allUsers: List<UserWithPlaylists> = userDB.playListDao().getUsersWithPlaylists()
                allUsers.forEach { userWithPlaylists ->
                    if(userWithPlaylists.user.username == binding.registerUsername.text.toString()){
                       binding.validationRegister.setText("User already exists!!")
                    }
                }
                binding.validationRegister.setText("");
                val user:User = User(userId = 123, username = binding.registerUsername.text.toString(), password = binding.registerPass.text.toString());
                val playList:Playlist = Playlist(playlistId = 123, userCreatorId = 123, playlistName = "recents");
                val pl2: List<Playlist> = listOf(playList)
                userDB.playListDao().insertUser(user);
                userDB.playListDao().insertPlayList(pl2);
            }
        }
        }
}