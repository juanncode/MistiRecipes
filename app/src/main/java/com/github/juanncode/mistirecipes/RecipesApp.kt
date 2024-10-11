package com.github.juanncode.mistirecipes

import android.app.Application
import com.github.juanncode.data.di.dataModule
import com.github.juanncode.mistirecipes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RecipesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RecipesApp)
            modules(
                appModule,
                dataModule
            )

        }
    }
}