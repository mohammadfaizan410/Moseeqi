package com.example.moseeqi.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.ctis487.roomdatabasewithonetomanyrelation.db.User
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserWithPlaylists
import com.example.moseeqi.HomePageActivity
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
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent);

        }
        binding.LoginBtn.setOnClickListener {
            Toast.makeText(this,"this is the login vclick", Toast.LENGTH_LONG).show()
            if(binding.registerUsername.text.toString() == ""){
                Toast.makeText(this, "", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if(binding.loginPass.text.toString() == ""){
                binding.loginValidation.setText("Please enter a password");
                return@setOnClickListener
            }
            else{

                val allUsers: List<UserWithPlaylists> = userDB.userDao().getAllUsersWithPlaylists()
                allUsers.forEach { usersWithPlaylists ->
                    if(usersWithPlaylists.user.username == binding.registerUsername.text.toString() && usersWithPlaylists.user.password == binding.loginPass.text.toString()){
                        sharedpreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
                        val editor = sharedpreferences.edit();
                        editor.putString(Constants.USERNAME_KEY, binding.registerUsername.text.toString());
                        editor.apply();
                        val intent = Intent(this, HomePageActivity::class.java)
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "user was not found", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

    // Assuming your layout file is named "login.xml"
    }
}
