package com.muliasahpira.txtalbum.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muliasahpira.txtalbum.data_mulia.AlbumRepository
import com.muliasahpira.txtalbum.ui.screen_mulia.detail_mulia.DetailAlbumViewModel
import com.muliasahpira.txtalbum.ui.screen_mulia.favorite_mulia.FavoriteAlbumViewModel
import com.muliasahpira.txtalbum.ui.screen_mulia.home_mulia.HomeScreenViewModel

class ViewModelFactory(private val repo: AlbumRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(DetailAlbumViewModel::class.java)) {
            return DetailAlbumViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(FavoriteAlbumViewModel::class.java)) {
            return FavoriteAlbumViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}