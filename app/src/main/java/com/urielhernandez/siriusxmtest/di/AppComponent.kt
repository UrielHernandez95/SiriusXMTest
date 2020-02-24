package com.urielhernandez.siriusxmtest.di

import com.urielhernandez.siriusxmtest.di.module.NetworkModule
import com.urielhernandez.siriusxmtest.ui.book.BookListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    /**
     * Injects required dependencies into the specified BookListViewModel.
     * @param BookListViewModel BookListViewModel in which to inject the dependencies
     */

    fun inject(bookListViewModel: BookListViewModel)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder

    }

}

