package com.ctis487.roomdatabasewithonetomanyrelation.db
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Playlist::class, Song::class], version = 3, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun songDao(): SongDao
}
