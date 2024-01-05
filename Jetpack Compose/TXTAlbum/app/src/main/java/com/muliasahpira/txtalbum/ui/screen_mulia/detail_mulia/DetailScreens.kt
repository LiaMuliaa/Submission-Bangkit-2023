package com.muliasahpira.txtalbum.ui.screen_mulia.detail_mulia

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muliasahpira.txtalbum.di_mulia.Injection
import com.muliasahpira.txtalbum.ui.ViewModelFactory
import com.muliasahpira.txtalbum.ui.common_mulia.UiState_Album
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme

@Composable
fun DetailScreens (
    albumtxtId: Int,
    viewModel: DetailAlbumViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navBack: () -> Unit,
    modif: Modifier = Modifier
) {
    viewModel.ui.collectAsState(initial = UiState_Album.LoadingYa).value.let { ui ->
        when (ui) {
            is UiState_Album.LoadingYa -> {
                viewModel.getAlbumtxtId(albumtxtId)
            }
            is UiState_Album.SuccessYeay -> {
                val listItem = ui.data
                DetailContent(
                    photo = listItem.photo,
                    id = listItem.id,
                    namaAlbum = listItem.namaAlbum,
                    release = listItem.release,
                    description = listItem.description,
                    genre = listItem.genre,
                    producer = listItem.producer,
                    listSong = listItem.listSong,
                    favorite = listItem.favorite,
                    navBack = navBack,
                    favoriteSelected = { albumtxtId, state ->
                        viewModel.addAlbum(albumtxtId, state)
                    }
                )
            }
            is UiState_Album.ErrorNih -> {}
        }
    }
}

@Composable
fun DetailContent(
    id: Int,
    namaAlbum: String,
    release: String,
    @DrawableRes photo: Int,
    description: String,
    genre: String,
    producer: String,
    listSong: String,
    favorite: Boolean,
    navBack: () -> Unit,
    favoriteSelected: (albumtxtId: Int, state: Boolean) -> Unit,
    modif: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(photo),
                contentDescription = namaAlbum,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 13.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(250.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp))
            )
            IconButton(
                onClick = navBack,
                modifier = Modifier
                    .offset(x = 6.dp, y = -250.dp)
                    .size(35.dp)
                    .testTag("backButton"),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Button Back to Home;"
                )
            }
            IconButton(
                onClick = {
                    favoriteSelected(id, favorite)
                },
                modifier = Modifier
                    .offset(x = 325.dp, y = -70.dp)
                    .size(35.dp)
                    .testTag("favoriteAlbum")
            ) {
                Icon(
                    imageVector = if (!favorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                    contentDescription = if (!favorite) "Your favorite Album?" else "Not your favorite Album",
                    tint = if (!favorite) Color.Black else Color.Red,
                )
            }
            Text(
                text = namaAlbum,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(7.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 13.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Genre of Album",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = genre,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Release of Album",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = release,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Producer of Album",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = producer,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp, end = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Deskripsi Album",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, top = 10.dp)
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 3.dp, top = 3.dp)
                )
                Text(
                    text = "Track",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, top = 17.dp)
                )
                Text(
                    text = listSong,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 3.dp, top = 3.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    TXTAlbumTheme {
        DetailContent(
            id = 1,
            namaAlbum = "THE NAME CHAPTER: TEMPTATION",
            release = "January 27, 2023",
            photo = com.muliasahpira.txtalbum.R.drawable.temptation,
            description = "The Name Chapter: TEMPTATION depicts youth on the brink of adulthood. After experiencing conflict and chaos, the boys feel a desire to postpone their growth and linger in the freedom of Neverland.",
            genre = "R&B/Soul, Korean Dance, K-Pop",
            producer = "Slow Rabbit; Dystinkt Beats; Smash David; Carson Thather; Slow Rabbit; Dystinkt Beats; Smash David; Carson Thather",
            listSong = "1. Devil by the Window 3’06”\n" + "2. Sugar Rush Ride 3’07”\n" + "3. Happy Fools (feat. Coi Leray 2’36”\n" +
                    "4. Tinnitus (돌멩이가 되고 싶어) 2’37”\n" + "5. Farewell, Neverland (네버랜드를 떠나며) 3’01”",
            favorite = true,
            navBack = { /*TODO*/ },
            favoriteSelected = { albumtxtId, modif -> }
        )
    }
}