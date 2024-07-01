package com.example.shoppinglist.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirestoreService : DatabaseService {
    private val db = Firebase.firestore
    private val shoppingListRef = db.collection("shoppingLists")

    override fun getAllShoppingItemLists(): Flow<List<ShoppingList>> {
        val stream = shoppingListRef.snapshots().map {
            it.toObjects<ShoppingList>()
        }
        return stream
    }

    override fun getShoppingItems(listId: String): Flow<ShoppingList?> {
        val stream = shoppingListRef.document(listId).snapshots().map {
            it.toObject<ShoppingList>()
        }
        return stream
    }

    override fun addShoppingItemList(list: ShoppingList) {
        val testList = ShoppingList(
            name = "TestList1", itemList = listOf(
                Item(name = "Bread", checked = true),
                Item(name = "Apple", checked = false),
                Item(name = "Milk", checked = false),
            )
        )
        Log.d("Firestore", "Adding List")
        shoppingListRef.add(testList)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }
}