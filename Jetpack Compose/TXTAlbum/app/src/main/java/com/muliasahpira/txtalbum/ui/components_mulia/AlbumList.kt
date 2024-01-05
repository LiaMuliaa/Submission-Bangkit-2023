package com.muliasahpira.txtalbum.ui.components_mulia

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muliasahpira.txtalbum.R
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme
import com.muliasahpira.txtalbum.ui.theme.Shapes

@Composable
fun AlbumList (
    id: Int,
    namaAlbum: String,
    release: String,
    photo: Int,
    genre: String,
    favorite: Boolean,
    favoriteSet: (id: Int, state: Boolean) -> Unit,
    modif: Modifier = Modifier,
) {
    Box(
        modifier = modif
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(Shapes.small)
            ) {
                Image(
                    painter = painterResource(photo),
                    contentDescription = "photo of Album",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = namaAlbum,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )

                )
                Text(
                    text = genre,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        tint = Color.Blue,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp, top = 4.dp)
                            .size(17.dp)
                    )
                    Text(
                        text = release,
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .padding(top = 4.dp)
                    )
                }
            }
        }
        Icon(
            imageVector = if (favorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Botton For Favorite",
            tint = if (!favorite) Color.Black else Color.Blue,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(18.dp)
                .testTag("favoriteButton")
                .clickable { favoriteSet(id, !favorite) }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AlbumListPreview() {
    TXTAlbumTheme {
        AlbumList(
            id = 1,
            namaAlbum = "THE DREAM CHAPTER: ETERNITY",
            release = "13 October 2023",
            photo = R.drawable.freefall,
            genre = "K-Pop",
            favorite = true,
            favoriteSet = { albumtxtId, modif -> }
        )
    }
}