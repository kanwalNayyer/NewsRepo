package com.hisham.news

import android.app.Application
import com.hisham.news.di.apiModule
import com.hisham.news.di.persistenceDataModule
import com.hisham.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class NewsApp: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApp)

            androidFileProperties()
            modules(
                listOf(
                    viewModelModule, persistenceDataModule, apiModule
                )
            )
        }

    }

}