package com.muliasahpira.txtalbum

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muliasahpira.txtalbum.ui.navigation_mulia.NavigationforItem
import com.muliasahpira.txtalbum.ui.navigation_mulia.Screen
import com.muliasahpira.txtalbum.ui.screen_mulia.detail_mulia.DetailScreens
import com.muliasahpira.txtalbum.ui.screen_mulia.favorite_mulia.FavoriteScreen
import com.muliasahpira.txtalbum.ui.screen_mulia.home_mulia.HomeScreen
import com.muliasahpira.txtalbum.ui.screen_mulia.myProfile_mulia.ProfileMulia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtAlbumApp(
    modif: Modifier = Modifier,
    nav: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by nav.currentBackStackEntryAsState()
    val routeNow = navBackStackEntry?.destination?.route

    Scaffold(
    bottomBar = {
        if (routeNow != Screen.DetailAlbumtxt.route) {
            BottomBar(nav)
        }
    },
    modifier = modif
    ) { innerPadding ->
        NavHost(
            navController = nav,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    moveToDetailPage = { albumtxtId ->
                        nav.navigate(Screen.DetailAlbumtxt.createRoute(albumtxtId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    moveToDetailPage = { albumtxtId ->
                        nav.navigate(Screen.DetailAlbumtxt.createRoute(albumtxtId))
                    }
                )
            }
            composable(Screen.ProfilMulia.route) {
                ProfileMulia()
            }
            composable(
                route = Screen.DetailAlbumtxt.route,
                arguments = listOf(
                    navArgument("albumtxtId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("albumtxtId") ?: -1
                DetailScreens(
                    albumtxtId = id,
                    navBack = {
                        nav.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    nav: NavHostController,
    modif: Modifier = Modifier
) {
    NavigationBar(
        modifier = modif
    ) {
        val navBackStackEntry by nav.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationforItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationforItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Rounded.Favorite,
                screen = Screen.Favorite
            ),
            NavigationforItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.ProfilMulia
            ),
        )
        NavigationBar {
            navigationItems.map { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        nav.navigate(item.screen.route) {
                            popUpTo(nav.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}