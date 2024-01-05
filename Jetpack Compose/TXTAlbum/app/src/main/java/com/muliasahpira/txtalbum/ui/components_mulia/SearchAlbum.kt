package com.muliasahpira.txtalbum.ui.components_mulia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muliasahpira.txtalbum.R
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAlbum (
    list: String,
    listChange: (String) -> Unit,
    modif: Modifier = Modifier
) {
    TextField(
        value = list,
        onValueChange = listChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "for Searching Album"
            )
        },
        placeholder = {
            Text(stringResource(id = R.string.search),
                fontSize = 13.sp)
        },
        shape = MaterialTheme.shapes.large,
        modifier = modif
            .padding(20.dp)
            .fillMaxWidth()
            .heightIn(min = 40.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun searchAlbumPreview() {
    TXTAlbumTheme {
        SearchAlbum(list = "Star", listChange = { })
    }
}