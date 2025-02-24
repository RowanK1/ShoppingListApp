package com.example.shoppinglist.data

import com.example.shoppinglist.model.Item
import com.example.shoppinglist.model.ShoppingList
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getAllShoppingItemLists(): Flow<List<ShoppingList>>
    fun addShoppingList(shoppingListName: String)
    fun getShoppingItems(listId: String): Flow<ShoppingList?>
    fun addShoppingItem(item: Item, shoppingListId: String)
}