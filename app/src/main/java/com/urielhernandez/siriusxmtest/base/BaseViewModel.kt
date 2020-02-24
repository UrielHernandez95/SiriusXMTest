package com.urielhernandez.siriusxmtest.base

import androidx.lifecycle.ViewModel
import com.urielhernandez.siriusxmtest.di.DaggerAppComponent
import com.urielhernandez.siriusxmtest.di.module.NetworkModule
import com.urielhernandez.siriusxmtest.ui.book.BookListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector = DaggerAppComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is BookListViewModel -> injector.inject(this)
        }
    }
}