package it.pierosilvestri.bookpedia.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import it.pierosilvestri.bookpedia.book.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> {
            Darwin.create()
        }

        single {
            DatabaseFactory()
        }
    }