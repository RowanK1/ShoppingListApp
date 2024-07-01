package com.example.shoppinglist.ui.shoppingListItems

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.data.Item
import com.example.shoppinglist.ui.AppViewModelProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemsScreen(
    viewModel: ShoppingItemsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Items") }) }
    ) { innerPadding ->
        ShoppingListItems(
            itemList = uiState.list.itemList,
            onItemClick = {},
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
private fun ShoppingListItems(
    itemList: List<Item>, onItemClick: (Item) -> Unit, modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {
        items(items = itemList) { item ->
            ShoppingListItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }
    }
}

@Composable
private fun ShoppingListItem(
    item: Item, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.weight(1f))
            Checkbox(checked = item.checked, onCheckedChange = null)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemListPreview() {
    ShoppingListItems(itemList = listOf(
        Item("1", "Bread", true),
        Item("2", "Apple", false),
        Item("3", "Milk", false),
    ), onItemClick = {})
}

@Preview(showBackground = true)
@Composable
fun ShoppingListItemPreview() {
    ShoppingListItem(
        Item("1", "Bread", true),
    )
}