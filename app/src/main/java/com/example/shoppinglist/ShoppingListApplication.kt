package com.example.shoppinglist

import android.app.Application
import com.example.shoppinglist.data.AppContainer
import com.example.shoppinglist.data.DefaultAppContainer

class ShoppingListApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
