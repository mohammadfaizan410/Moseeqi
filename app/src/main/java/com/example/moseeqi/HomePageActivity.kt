package com.example.moseeqi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.ctis487.roomdatabasewithonetomanyrelation.db.UserRoomDatabase
import com.example.moseeqi.constants.Constants
import com.example.moseeqi.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)

        val userDB: UserRoomDatabase by lazy {
            Room.databaseBuilder(this, UserRoomDatabase::class.java, "PlayListDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        val user = userDB.userDao().getUserByUsername(sharedPreferences.getString(Constants.USERNAME_KEY,"")!!)
        val playlists = userDB.playlistDao().getPlaylistsByUserId(user!!.userId)
        val likedSongs = userDB.songDao().getSongsByPlaylistId(playlists[0].playlistId)
        val recentSongs = userDB.songDao().getSongsByPlaylistId(playlists[1].playlistId)
        Toast.makeText(this, "${user.toString()}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "${playlists.toString()}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "${likedSongs.toString()}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "${recentSongs.toString()}", Toast.LENGTH_LONG).show()

        binding.btnLikedSongs.setOnClickListener {
            startActivity(Intent(this, LikedSongsActivity::class.java))
        }

        binding.btnRecentSongs.setOnClickListener {
            startActivity(Intent(this, RecentSongsActivity::class.java))
        }
        binding.logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit();
            editor.putString(Constants.USERNAME_KEY, "")
            editor.putLong(Constants.USERID_KEY, 0)
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
