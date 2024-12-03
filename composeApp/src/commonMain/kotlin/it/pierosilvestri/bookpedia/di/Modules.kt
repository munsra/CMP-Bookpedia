package it.pierosilvestri.bookpedia.di

import it.pierosilvestri.bookpedia.book.data.network.KtorRemoteBookDataSource
import it.pierosilvestri.bookpedia.book.data.network.RemoteBookDataSource
import it.pierosilvestri.bookpedia.book.data.repository.DefaultBookRepository
import it.pierosilvestri.bookpedia.book.domain.BookRepository
import it.pierosilvestri.bookpedia.book.presentation.book_list.BookListViewModel
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

    viewModelOf(::BookListViewModel)
}