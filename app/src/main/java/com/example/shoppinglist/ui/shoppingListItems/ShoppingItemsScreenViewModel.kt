package com.example.shoppinglist.ui.shoppingListItems

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.model.ShoppingList
import com.example.shoppinglist.data.ShoppingListRepository
import com.example.shoppinglist.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class ShoppingItemsScreenUiState(
    val list: ShoppingList,
)

@HiltViewModel
class ShoppingItemsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {
    private val TIMEOUT_MILLIS = 5_000L

    private val shoppingListId: String = checkNotNull(savedStateHandle["shoppingListId"])

    val uiState: StateFlow<ShoppingItemsScreenUiState> =
        shoppingListRepository.getShoppingItemsStream(listId = shoppingListId)
            .map { ShoppingItemsScreenUiState(it!!) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ShoppingItemsScreenUiState(ShoppingList())
            )

    var newItemName by mutableStateOf("")
        private set

    fun updateNewItemName(name: String) {
        newItemName = name
    }

    fun getShoppingListLength() = uiState.value.list.itemList.size

    fun addNewItem(itemName: String) {
        val newItemId = (getShoppingListLength() + 1).toString()
        shoppingListRepository.addShoppingItem(Item(newItemId, itemName), shoppingListId)
    }
}

