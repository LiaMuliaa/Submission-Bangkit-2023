package com.muliasahpira.txtalbum.ui.screen_mulia.detail_mulia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muliasahpira.txtalbum.data_mulia.AlbumRepository
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailAlbumViewModel (
    private val repo: AlbumRepository
) : ViewModel() {
    private val _ui: MutableStateFlow<UiState_Album<AlbumTubatu>> = MutableStateFlow(UiState_Album.LoadingYa)
    val ui: StateFlow<UiState_Album<AlbumTubatu>>
        get() = _ui

    fun getAlbumtxtId(albumtxtId: Int) {
        viewModelScope.launch {
            _ui.value = UiState_Album.LoadingYa
            _ui.value = UiState_Album.SuccessYeay(repo.getAlbumById(albumtxtId))
        }
    }

    fun addAlbum(albumId: Int, state: Boolean) {
        viewModelScope.launch {
            repo.updateAlbumTXT(albumId, !state)
                .collect { isAdd -> if (isAdd) getAlbumtxtId(albumId) }
        }
    }
}