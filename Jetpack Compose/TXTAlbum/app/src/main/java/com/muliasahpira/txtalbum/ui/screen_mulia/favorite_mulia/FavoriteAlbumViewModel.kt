package com.muliasahpira.txtalbum.ui.screen_mulia.favorite_mulia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muliasahpira.txtalbum.data_mulia.AlbumRepository
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteAlbumViewModel(
    private val repo: AlbumRepository
) : ViewModel() {
    private val _ui: MutableStateFlow<UiState_Album<List<AlbumTubatu>>> = MutableStateFlow(UiState_Album.LoadingYa)
    val ui: StateFlow<UiState_Album<List<AlbumTubatu>>> get() = _ui

    fun addAlbumFavorite() {
        viewModelScope.launch {
            repo.getAlbumFavorite()
                .catch {
                    _ui.value = UiState_Album.ErrorNih(it.message.toString())
                }
                .collect {
                    _ui.value = UiState_Album.SuccessYeay(it)
                }
        }
    }

    fun albumFavoriteUpdate(albumtxtId: Int, state: Boolean) {
        repo.updateAlbumTXT(albumtxtId, state)
        addAlbumFavorite()
    }
}