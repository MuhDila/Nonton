package com.muhdila.nonton

import android.app.Application
import com.muhdila.core.di.databaseModule
import com.muhdila.core.di.networkModule
import com.muhdila.core.di.repositoryModule
import com.muhdila.nonton.di.useCaseModule
import com.muhdila.nonton.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
