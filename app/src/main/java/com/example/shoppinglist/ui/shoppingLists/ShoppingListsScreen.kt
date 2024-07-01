package com.example.shoppinglist.ui.shoppingLists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppinglist.R
import com.example.shoppinglist.data.ShoppingList
import com.example.shoppinglist.ui.AppViewModelProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListsScreen(
    navigateToShoppingItems: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShoppingListsScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.shopping_lists_title)) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.addShoppingListTest() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "ADD"
                )
            }
        }
    ) { innerPadding ->
        ShoppingLists(
            shoppingLists = uiState.shoppingLists,
            onItemClick = { navigateToShoppingItems(it) },
            modifier = Modifier.padding(innerPadding)
        )

    }
}

@Composable
private fun ShoppingLists(
    shoppingLists: List<ShoppingList>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = shoppingLists, key = { it.id }) { item ->
            ShoppingList(
                shoppingList = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable {
                        onItemClick(item.id)
                    })
        }
    }
}

@Composable
private fun ShoppingList(
    shoppingList: ShoppingList, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
        ) {
            Text(
                text = shoppingList.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.weight(1f))
            Text(text = shoppingList.id)
        }
    }
}
