package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

// User entity
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0,
    val username: String,
    val password: String
)

// Playlist entity
@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val playlistId: Long = 0,
    val ownerId: Long, // User ID
    val name: String
)

// Song entity
@Entity
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Long = 0,
    val playlistId: Long, // Playlist ID
    val title: String,
    val streamURL: String,
    val artworkURL: String?
)


data class UserWithPlaylistsAndSongs(
    @Embedded val user: User,
    @Relation(parentColumn = "userId", entityColumn = "ownerId")
    val playlists: List<PlaylistWithSongs>
)

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(parentColumn = "playlistId", entityColumn = "playlistId")
    val songs: List<Song>
)