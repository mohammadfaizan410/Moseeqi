package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.*

@Dao
interface UserDao {
    // Create: Insert a new user into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    // Read: Fetch all users
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    // Read: Retrieve a single user by their username
    @Query("SELECT * FROM User WHERE username = :username")
    fun getUserByUsername(username: String): User?

    // Read: Retrieves all users with their playlists and songs
    @Transaction
    @Query("SELECT * FROM User")
    fun getAllUsersWithPlaylists(): List<UserWithPlaylists>

    // Update: Update an existing user's information
    @Update
    fun updateUser(user: User)

    // Delete: Remove a user from the database
    @Delete
    fun deleteUser(user: User)
}

@Dao
interface PlaylistDao {
    // Create: Inserts a new playlist
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaylist(playlist: Playlist): Long

    // Read: Retrieves all playlists
    @Query("SELECT * FROM Playlist")
    fun getAllPlaylists(): List<Playlist>

    // Read: Retrieves a playlist by ID
    @Query("SELECT * FROM Playlist WHERE playlistId = :playlistId")
    fun getPlaylistById(playlistId: Long): Playlist?

    // Update: Updates an existing playlist
    @Update
    fun updatePlaylist(playlist: Playlist)

    // Delete: Deletes a playlist
    @Delete
    fun deletePlaylist(playlist: Playlist)
}

@Dao
interface SongDao {
    // Create: Inserts a new song
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(song: Song): Long

    // Create: Inserts multiple songs at once
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(songs: List<Song>)

    // Read: Fetches all songs
    @Query("SELECT * FROM Song")
    fun getAllSongs(): List<Song>

    // Update: Updates an existing song
    @Update
    fun updateSong(song: Song)

    // Delete: Deletes a song
    @Delete
    fun deleteSong(song: Song)
}
