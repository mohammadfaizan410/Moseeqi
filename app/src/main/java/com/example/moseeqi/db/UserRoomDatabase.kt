package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.Database
import androidx.room.RoomDatabase
@Database( entities = [User::class, Playlist::class], version = 1)
abstract class UserRoomDatabase:RoomDatabase() {
    abstract fun playListDao():UserDAO
}