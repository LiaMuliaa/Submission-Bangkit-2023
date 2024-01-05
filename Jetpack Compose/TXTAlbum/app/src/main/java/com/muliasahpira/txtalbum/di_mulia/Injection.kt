package com.muliasahpira.txtalbum.di_mulia

import com.muliasahpira.txtalbum.data_mulia.AlbumRepository

object Injection {
    fun provideRepository(): AlbumRepository {
        return AlbumRepository.getInstance()
    }
}