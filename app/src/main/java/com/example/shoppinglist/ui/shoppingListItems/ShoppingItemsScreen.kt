package com.example.shoppinglist.ui.shoppingListItems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppinglist.model.Item
import com.example.shoppinglist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemsScreen(
    viewModel: ShoppingItemsScreenViewModel = hiltViewModel<ShoppingItemsScreenViewModel>()
) {
    val uiState by viewModel.uiState.collectAsState()
    val showAddItemDialog = remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Items") }) }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showAddItemDialog.value = true },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = "ADD"
            )
        }
    }) { innerPadding ->

        if (showAddItemDialog.value) {
            AddItemDialog(onDismissRequest = {
                showAddItemDialog.value = false
                viewModel.updateNewItemName("")
            },
                newItemName = viewModel.newItemName,
                onNewItemNameChange = { newItemName -> viewModel.updateNewItemName(newItemName) },
                onConfirmClick = { newItemName -> viewModel.addNewItem(newItemName) })
        }

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
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
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

@Composable
fun AddItemDialog(
    onDismissRequest: () -> Unit,
    newItemName: String,
    onNewItemNameChange: (String) -> Unit,
    onConfirmClick: (String) -> Unit
) {
    AlertDialog(title = {
        Text(text = "Add a new item")
    }, text = {
        OutlinedTextField(value = newItemName, onValueChange = { onNewItemNameChange(it) })
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmClick(newItemName)
            onDismissRequest()
        }) {
            Text("Add")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("Cancel")
        }
    })
}

@Preview
@Composable
fun AddItemDialogPreview() {
    val showAddItemDialog = remember { mutableStateOf(true) }

    if (showAddItemDialog.value) {
        AddItemDialog(onDismissRequest = { showAddItemDialog.value = false },
            newItemName = "TEST",
            onNewItemNameChange = {},
            onConfirmClick = {})
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