package com.example.shoppinglist.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shoppinglist.ShoppingListApplication
import com.example.shoppinglist.ui.shoppingListItems.ShoppingItemsScreenViewModel
import com.example.shoppinglist.ui.shoppingLists.ShoppingListsScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ShoppingListsScreenViewModel(
                shoppingListApplication().container.shoppingListRepository,
            )
        }

        initializer {
            ShoppingItemsScreenViewModel(
                this.createSavedStateHandle(),
                shoppingListApplication().container.shoppingListRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [ShoppingListApplication].
 */
fun CreationExtras.shoppingListApplication(): ShoppingListApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShoppingListApplication)
