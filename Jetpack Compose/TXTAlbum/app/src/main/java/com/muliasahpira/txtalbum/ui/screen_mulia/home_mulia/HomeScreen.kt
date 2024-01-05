package com.muliasahpira.txtalbum.ui.screen_mulia.home_mulia

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muliasahpira.txtalbum.R
import com.muliasahpira.txtalbum.di_mulia.Injection
import com.muliasahpira.txtalbum.model_mulia.AlbumTubatu
import com.muliasahpira.txtalbum.ui.ViewModelFactory
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import com.muliasahpira.txtalbum.ui.components_mulia.AlbumList
import com.muliasahpira.txtalbum.ui.components_mulia.DataKosong
import com.muliasahpira.txtalbum.ui.components_mulia.SearchAlbum
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme

@Composable
fun HomeScreen(
    modif: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    moveToDetailPage: (Int) -> Unit,
) {
    val list by viewModel.list
    viewModel.ui.collectAsState(initial = UiState_Album.LoadingYa).value.let { uiState ->
        when (uiState) {
            is UiState_Album.LoadingYa -> {
                viewModel.searchbar(list)
            }
            is UiState_Album.SuccessYeay -> {
                HomeContent(
                    list = list,
                    listUpdate = viewModel::searchbar,
                    listAlbumtxt = uiState.data,
                    favoriteSelected = { id, state -> viewModel.albumUpdate(id, state)
                    },
                    moveToDetailPage = moveToDetailPage
                )
            }
            is UiState_Album.ErrorNih -> {}
        }
    }
}

@Composable
fun HomeContent(
    list: String,
    listUpdate: (String) -> Unit,
    listAlbumtxt: List<AlbumTubatu>,
    favoriteSelected: (id: Int, newState: Boolean) -> Unit,
    modif: Modifier = Modifier,
    moveToDetailPage: (Int) -> Unit,
) {
    Column {
        SearchAlbum(
            list = list,
            listChange = listUpdate,
            Modifier.background(colorResource(id = R.color.purple_700))
        )
        if (listAlbumtxt.isNotEmpty()) {
            AlbumTXTListItem(
                listAlbumtxt = listAlbumtxt,
                favoriteSelected = favoriteSelected,
                moveToDetailPage = moveToDetailPage
            )
        } else {
            DataKosong(
                peringatan = stringResource(R.string.dataEmpty),
                modif = Modifier.testTag("emptyData")
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumTXTListItem(
    listAlbumtxt: List<AlbumTubatu>,
    favoriteSelected: (id: Int, newState: Boolean) -> Unit,
    modif: Modifier = Modifier,
    moveToDetailPage: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 12.dp,
            end = 12.dp,
            bottom = 17.dp,
            top = 17.dp
        ),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        modifier = modif
            .testTag("listAlbum")
    ) {
        items(listAlbumtxt, key = { it.id }) {item ->  
            AlbumList(
                id = item.id,
                namaAlbum = item.namaAlbum,
                release = item.release,
                photo = item.photo,
                genre = item.genre,
                favorite = item.favorite,
                favoriteSet = favoriteSelected,
                modif = Modifier
                    .clickable { moveToDetailPage(item.id) }
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview(){
    TXTAlbumTheme {
        HomeScreen(moveToDetailPage = { })
    }
}
