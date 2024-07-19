package com.example.shoppinglist.di

import com.example.shoppinglist.data.DatabaseService
import com.example.shoppinglist.data.FirestoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideFirestoreService(): DatabaseService {
        return FirestoreService()
    }
}