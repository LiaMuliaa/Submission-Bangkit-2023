package com.muliasahpira.txtalbum.ui.components_mulia

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muliasahpira.txtalbum.ui.theme.TXTAlbumTheme

@Composable
fun DataKosong (
    peringatan: String,
    modif: Modifier = Modifier,
){
   Box(modifier = modif.fillMaxSize(),
       contentAlignment = Alignment.Center
   ){
       Text(text = peringatan)
   } 
}

@Composable
@Preview(showBackground = true)
fun DataKosongPreview() {
    TXTAlbumTheme {
        DataKosong(peringatan = "Tidak ada Album")
    }
}