package com.example.moseeqi.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserWithPlaylists
import com.example.moseeqi.MainActivity
import com.example.moseeqi.constants.Constants
import com.example.moseeqi.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: LoginBinding;
        lateinit var sharedpreferences: SharedPreferences

        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater);
        setContentView(binding.root);
        val userDB: UserRoomDatabase by lazy {
            Room.databaseBuilder(this, UserRoomDatabase::class.java, "PlayListDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        binding.LoginBtn.setOnClickListener {
            if(binding.registerUsername.text.toString() == ""){
                binding.loginValidation.setText("please enter a username!");
                return@setOnClickListener
            }else if(binding.loginPass.text.toString() == ""){
                binding.loginValidation.setText("Please enter a password");
                return@setOnClickListener
            }
            else{
                val allUsers: List<UserWithPlaylists> = userDB.playListDao().getUsersWithPlaylists()
                allUsers.forEach { userWithPlaylists ->
                    if(userWithPlaylists.user.username == binding.registerUsername.text.toString() && userWithPlaylists.user.password == binding.loginPass.text.toString()){
                        sharedpreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
                        val editor = sharedpreferences.edit();
                        editor.putString(Constants.USERNAME_KEY, binding.registerUsername.text.toString());
                        editor.apply();
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent);
                    }
                }

            }
        }

    // Assuming your layout file is named "login.xml"
    }
}
