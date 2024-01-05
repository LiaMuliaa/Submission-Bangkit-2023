package com.muliasahpira.txtalbum.ui.screen_mulia.home_mulia

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muliasahpira.txtalbum.data_mulia.AlbumRepository
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeScreenViewModel (
    private val repo: AlbumRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState_Album<List<AlbumTubatu>>> = MutableStateFlow(UiState_Album.LoadingYa)
    val ui: StateFlow<UiState_Album<List<AlbumTubatu>>>
        get() = _uiState

    private val _list = mutableStateOf("")
    val list: State<String> get() = _list

    fun albumUpdate(id: Int, stateNow: Boolean) = viewModelScope.launch {
        repo.updateAlbumTXT(id, stateNow)
            .collect { updatedNow ->
                if (updatedNow) searchbar(_list.value)
            }
    }

    fun searchbar(newQuery: String) = viewModelScope.launch {
        _list.value = newQuery
        repo.searchingAlbumTXT(_list.value)
            .catch {
                _uiState.value = UiState_Album.ErrorNih(it.message.toString())
            }
            .collect {
                _uiState.value = UiState_Album.SuccessYeay(it)
            }
    }
}