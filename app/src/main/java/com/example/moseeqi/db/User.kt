package com.ctis487.roomdatabasewithonetomanyrelation.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.lang.reflect.Constructor

@Entity
data class User(
    @PrimaryKey
    val userId: Long,
    val username: String,
    val password: String
){
    override fun toString(): String {
        return "\nUser\nId: $userId, name: $username, password: $password\n"
    }
}

@Entity
data class Playlist(
    @PrimaryKey
    val playlistId: Long,
    val userCreatorId: Long,
    val playlistName: String
){
    override fun toString(): String {
        return "\nPlaylist\nId: $playlistId, user Creator Id:$userCreatorId, playlistName:$playlistName\n"
    }
}

class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val playlists: List<Playlist> //that List will store the playList of user. 1 user may have many playlist

){
    override fun toString(): String {
        return "User With Playlists\n$user\n\t$playlists"
    }
}