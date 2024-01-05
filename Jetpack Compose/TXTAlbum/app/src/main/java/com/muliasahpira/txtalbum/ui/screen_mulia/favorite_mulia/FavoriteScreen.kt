package com.muliasahpira.txtalbum.ui.screen_mulia.favorite_mulia

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muliasahpira.txtalbum.di_mulia.Injection
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import com.muliasahpira.txtalbum.ui.ViewModelFactory
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import com.muliasahpira.txtalbum.ui.components_mulia.DataKosong
import com.muliasahpira.txtalbum.ui.screen_mulia.home_mulia.AlbumTXTListItem

@Composable
fun FavoriteScreen (
    moveToDetailPage: (Int) -> Unit,
    viewModel: FavoriteAlbumViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    modif: Modifier = Modifier
) {
    viewModel.ui.collectAsState(initial = UiState_Album.LoadingYa).value.let { ui ->
        when (ui) {
            is UiState_Album.LoadingYa -> {
                viewModel.addAlbumFavorite()
            }
            is UiState_Album.SuccessYeay -> {
                FavoriteContent(
                    listAlbumtxt = ui.data,
                    moveToDetailPage = moveToDetailPage,
                    favoriteSelected = { albumtxtId, state ->
                        viewModel.albumFavoriteUpdate(albumtxtId, state)
                    }
                )
            }
            is UiState_Album.ErrorNih -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listAlbumtxt: List<AlbumTubatu>,
    moveToDetailPage: (Int) -> Unit,
    favoriteSelected: (albumtxtId: Int, state: Boolean) -> Unit,
    modif: Modifier = Modifier,
) {
    Column (
        modifier = Modifier
    ) {
        if (listAlbumtxt.isNotEmpty()){
            AlbumTXTListItem(listAlbumtxt = listAlbumtxt, favoriteSelected = favoriteSelected, moveToDetailPage = moveToDetailPage)
        } else{
            DataKosong(peringatan = "There is no your favorite Album")
        }
    }
}