package com.ctis487.roomdatabasewithonetomanyrelation.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserById(userId: Long): User?

    @Query("SELECT * FROM User WHERE username = :username")
    fun getUserByUsername(username: String): User?
}

@Dao
interface PlaylistDao {
    @Insert
    fun insertPlaylist(playlist: Playlist): Long

    @Query("SELECT * FROM Playlist WHERE ownerId = :userId")
    fun getPlaylistsByUserId(userId: Long): List<Playlist>
}

@Dao
interface SongDao {
    @Insert
    fun insertSong(song: Song): Long

    @Query("SELECT * FROM Song WHERE playlistId = :playlistId")
    fun getSongsByPlaylistId(playlistId: Long): List<Song>
}

@Dao
interface UserWithPlaylistsAndSongsDao {
    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserWithPlaylistsAndSongs(userId: Long): UserWithPlaylistsAndSongs

    @Transaction
    @Query("SELECT * FROM Playlist WHERE ownerId = :userId")
    fun getPlaylistsWithSongsByUserId(userId: Long): List<PlaylistWithSongs>

    @Transaction
    @Query("SELECT * FROM Playlist WHERE playlistId = :playlistId")
    fun getPlaylistWithSongs(playlistId: Long): PlaylistWithSongs
}
