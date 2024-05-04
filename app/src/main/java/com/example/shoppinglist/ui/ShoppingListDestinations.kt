package com.example.shoppinglist.ui


interface Destination {
    val route: String

}

object ShoppingListsDestination : Destination {
    override val route: String = "ShoppingLists"

}

object ShoppingItemsDestination : Destination {
    override val route: String = "Items"
}