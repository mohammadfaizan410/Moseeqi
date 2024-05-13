package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.*

// User entity
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) // Autogenerate ID for new users
    val userId: Long = 0,
    val username: String,
    val password: String
) {
    override fun toString(): String {
        return "\nUser\nId: $userId, name: $username, password: $password\n"
    }
}

// Playlist entity
@Entity(foreignKeys = [
    ForeignKey(entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE)
])
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val playlistId: Long = 0,
    val name: String,
    val ownerId: Long // Link to the User entity
)

// Updated Song entity to reflect the attributes from the Java Track class
@Entity
data class Song(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val songId: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "stream_url")
    val streamURL: String,

    @ColumnInfo(name = "artwork_url")
    val artworkURL: String?
)

// Cross-reference table for many-to-many relationship between Playlist and Song
@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val songId: Long
)

// Database view to access the songs in a playlist
data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)

// Database view to access the playlists of a user
data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        entity = Playlist::class,
        parentColumn = "userId",
        entityColumn = "ownerId"
    )
    val playlists: List<PlaylistWithSongs>
)
