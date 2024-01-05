package com.muliasahpira.txtalbum.ui.screen_mulia.myProfile_mulia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muliasahpira.txtalbum.R
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme

@Composable
fun ProfileMulia (
    modif: Modifier = Modifier,
) {
    val photo = painterResource(id = R.drawable.mulia)
    val myName = "Mulia Sahpira"
    val myEmail = "muliasahpira23@gmail.com"

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = photo,
                contentDescription = "Foto Mulia",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 230.dp)
                    .align(Alignment.CenterHorizontally)
                    .height(250.dp)
                    .width(250.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomStart = 15.dp,
                            bottomEnd = 15.dp
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 13.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Icon for Namaku",
                        modifier = Modifier
                            .padding(top = 20.dp, start = 87.dp)
                            .size(25.dp)
                    )
                    Text(
                        text = myName,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp, start = 7.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Icon for Emailku",
                        modifier = Modifier
                            .padding(start = 60.dp)
                            .size(20.dp)
                    )
                    Text(
                        text = myEmail,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(start = 7.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMuliaPreview() {
    TXTAlbumTheme {
        ProfileMulia()
    }
}