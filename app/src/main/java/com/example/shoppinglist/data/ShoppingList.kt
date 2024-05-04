package com.example.shoppinglist.data

import com.google.firebase.firestore.DocumentId

data class ShoppingList(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val itemList: List<Item> = listOf()
)
