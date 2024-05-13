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
        binding.btnNavRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.LoginBtn.setOnClickListener {
            if(binding.registerUsername.text.toString() == ""){
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if(binding.loginPass.text.toString() == ""){
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                val user: User? = userDB.userDao().getUserByUsername(binding.registerUsername.text.toString());
                if(user == null) {
                    Toast.makeText(this, "No User Was Found!", Toast.LENGTH_LONG).show()
                }
             else{
                 val res:Boolean = user.password == binding.loginPass.text.toString()
                    if(res == true){
                        Toast.makeText(this, "Password Matched", Toast.LENGTH_SHORT).show()
                   sharedpreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
                   val editor = sharedpreferences.edit()
                   editor.putString(Constants.USERNAME_KEY, user.username)
                    editor.putLong(Constants.USERID_KEY, user.userId)
                    editor.apply()
                   val intent = Intent(this, HomePageActivity::class.java)
                   startActivity(intent)
                   }
                    else{
                        Toast.makeText(this, "Invalid Password!", Toast.LENGTH_LONG).show()
                    }
               }

            }
        }
    }
}
