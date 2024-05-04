package com.example.shoppinglist.ui.shoppingListItems

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.data.ShoppingList
import com.example.shoppinglist.data.ShoppingListRepository
import com.example.shoppinglist.ui.shoppingLists.ShoppingListsScreenUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ShoppingItemsScreenUiState(val list : ShoppingList)

class ShoppingItemsScreenViewModel (savedStateHandle: SavedStateHandle, private val shoppingListRepository : ShoppingListRepository): ViewModel() {
    private val TIMEOUT_MILLIS = 5_000L

    private val shoppingListId: String = checkNotNull(savedStateHandle["shoppingListId"])

    val uiState: StateFlow<ShoppingItemsScreenUiState> = shoppingListRepository.getShoppingItemsStream(listId = shoppingListId).map { ShoppingItemsScreenUiState(it!!) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ShoppingItemsScreenUiState(ShoppingList())
        )
}

