package com.muliasahpira.txtalbum.model_mulia

data class AlbumTubatu (
    val id: Int,
    val namaAlbum: String,
    val release: String,
    val photo: Int,
    val description: String,
    val genre: String,
    val producer: String,
    val listSong: String,
    var favorite: Boolean = false
)