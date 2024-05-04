package com.example.shoppinglist.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


interface AppContainer {
    val shoppingListRepository: ShoppingListRepository
}

class DefaultAppContainer: AppContainer{
    private val firestoreService = FirestoreService()

    override val shoppingListRepository: ShoppingListRepository by lazy {
        FirestoreShoppingListRepository(firestoreService)
    }
}