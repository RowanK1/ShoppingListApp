package com.example.shoppinglist.data

import com.example.shoppinglist.model.ShoppingList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface ShoppingListRepository {
    fun getAllShoppingListsStream(): Flow<List<ShoppingList>>

    fun getShoppingItemsStream(listId: String): Flow<ShoppingList?>

    fun addShoppingList(shoppingList: ShoppingList)
}

class FirestoreShoppingListRepository @Inject constructor(
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