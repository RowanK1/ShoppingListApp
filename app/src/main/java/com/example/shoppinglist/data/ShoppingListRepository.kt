package com.example.shoppinglist.data

import kotlinx.coroutines.flow.Flow


interface ShoppingListRepository {
    fun getAllShoppingListsStream(): Flow<List<ShoppingList>>

    fun getShoppingItemsStream(listId: String): Flow<ShoppingList?>

    fun addShoppingList(shoppingList: ShoppingList)
}

class FirestoreShoppingListRepository(
    private val databaseService: DatabaseService
) : ShoppingListRepository {
    override fun getAllShoppingListsStream(): Flow<List<ShoppingList>> =
        databaseService.getAllShoppingItemLists()

    override fun getShoppingItemsStream(listId: String): Flow<ShoppingList?> =
        databaseService.getShoppingItems(listId)

    override fun addShoppingList(shoppingList: ShoppingList) {
        databaseService.addShoppingItemList(shoppingList)
    }


}