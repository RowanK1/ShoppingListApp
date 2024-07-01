package com.example.shoppinglist.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppinglist.ui.shoppingListItems.ShoppingItemsScreen
import com.example.shoppinglist.ui.shoppingLists.ShoppingListsScreen

@Composable
fun ShoppingListNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ShoppingListsDestination.route,
        modifier = modifier
    ) {
        composable(route = ShoppingListsDestination.route) {
            ShoppingListsScreen(
                navigateToShoppingItems =
                {
                    navController.navigate("${ShoppingItemsDestination.route}/${it}")
                }
            )
        }
        composable(
            route = "${ShoppingItemsDestination.route}/{shoppingListId}",
            arguments = listOf(navArgument("shoppingListId") { type = NavType.StringType })
        ) {
            ShoppingItemsScreen()

        }

    }

}

@Composable
fun ShoppingListApp() {
    val navController = rememberNavController()
    ShoppingListNavHost(navController = navController)
}