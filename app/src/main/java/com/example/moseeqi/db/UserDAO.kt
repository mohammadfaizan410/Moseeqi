package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDAO {
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPlaylists(): List<UserWithPlaylists>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayList(playList:List<Playlist>)
}