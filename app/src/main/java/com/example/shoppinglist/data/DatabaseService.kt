package com.example.shoppinglist.data

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getAllShoppingItemLists(): Flow<List<ShoppingList>>
    fun addShoppingItemList(list: ShoppingList)
    fun getShoppingItems(listId: String): Flow<ShoppingList?>
}