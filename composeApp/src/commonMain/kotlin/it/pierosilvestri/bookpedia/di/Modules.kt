package it.pierosilvestri.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import it.pierosilvestri.bookpedia.book.data.database.DatabaseFactory
import it.pierosilvestri.bookpedia.book.data.database.FavoriteBookDatabase
import it.pierosilvestri.bookpedia.book.data.network.KtorRemoteBookDataSource
import it.pierosilvestri.bookpedia.book.data.network.RemoteBookDataSource
import it.pierosilvestri.bookpedia.book.data.repository.DefaultBookRepository
import it.pierosilvestri.bookpedia.book.domain.BookRepository
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListViewModel
import it.pierosilvestri.bookpedia.book.presentation.SelectedBookViewModel
import it.pierosilvestri.bookpedia.book.presentation.book_detail.BookDetailViewModel
import it.pierosilvestri.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
       get<DatabaseFactory>().create()
           .setDriver(BundledSQLiteDriver())
           .build()
    }

    single { get<FavoriteBookDatabase>().favoriteBooksDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}