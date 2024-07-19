package com.example.shoppinglist.ui.shoppingLists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.shoppinglist.model.ShoppingList
import com.example.shoppinglist.data.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class ShoppingListsScreenUiState(val shoppingLists: List<ShoppingList> = listOf())

@HiltViewModel
class ShoppingListsScreenViewModel @Inject constructor(private val shoppingListRepository: ShoppingListRepository) :
    ViewModel() {
    private val TIMEOUT_MILLIS = 5_000L

    val uiState: StateFlow<ShoppingListsScreenUiState> =
        shoppingListRepository.getAllShoppingListsStream().map { ShoppingListsScreenUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ShoppingListsScreenUiState()
            )

    fun addShoppingListTest() {
        shoppingListRepository.addShoppingList(ShoppingList("", "", listOf()))
    }
}

