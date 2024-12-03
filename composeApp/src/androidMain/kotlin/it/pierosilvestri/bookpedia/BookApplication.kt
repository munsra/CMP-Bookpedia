package it.pierosilvestri.bookpedia

import android.app.Application
import it.pierosilvestri.bookpedia.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}