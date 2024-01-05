package com.muliasahpira.txtalbum.ui.navigation_mulia

sealed class Screen(val route: String) {
    object Home : Screen("beranda")
    object Favorite : Screen("favorite")
    object ProfilMulia : Screen("profileMulia")
    object DetailAlbumtxt : Screen("home/{albumtxtId}") {
        fun createRoute(albumtxtId: Int) = "home/$albumtxtId"
    }
}