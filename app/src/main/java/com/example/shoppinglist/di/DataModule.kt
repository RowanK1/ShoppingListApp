package com.example.shoppinglist.di

import com.example.shoppinglist.data.FirestoreShoppingListRepository
import com.example.shoppinglist.data.ShoppingListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideFirestoreShoppingListRepository(repository: FirestoreShoppingListRepository): ShoppingListRepository {
        return repository
    }
}