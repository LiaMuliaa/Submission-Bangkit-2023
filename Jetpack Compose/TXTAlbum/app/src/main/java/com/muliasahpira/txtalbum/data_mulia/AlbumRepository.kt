package com.muliasahpira.txtalbum.data_mulia

import com.muliasahpira.txtalbum.model_mulia.AlbumDataSource
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class AlbumRepository {
    private val albumRepo = mutableListOf<AlbumTubatu>()
    init {
        if (albumRepo.isEmpty()) {
            AlbumDataSource.dummyList.forEach {
                albumRepo.add(it)
            }
        }
    }

    fun getAlbumFavorite(): Flow<List<AlbumTubatu>> {
        return flowOf(albumRepo.filter { it.favorite })
    }

    fun getAlbumById(albumtxtId: Int): AlbumTubatu {
        return albumRepo.first {
            it.id == albumtxtId
        }
    }

    fun updateAlbumTXT(albumtxtId: Int, newCount: Boolean): Flow<Boolean> {
        val index = albumRepo.indexOfFirst { it.id == albumtxtId }
        val result = if (index >= 0) {
            val albumtxt = albumRepo[index]
            albumRepo[index] =
                albumtxt.copy(favorite = newCount)
            true
        } else{
            false
        }
        return flowOf(result)
    }

    fun searchingAlbumTXT(query: String) = flow {
        val listData = albumRepo.filter {
            it.namaAlbum.contains(query, ignoreCase = true)
        }
        emit(listData)
    }

    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(): AlbumRepository = instance ?: synchronized(this) {
            AlbumRepository().apply { instance = this }
        }
    }
}