package com.hisham.news.di

import android.content.Context
import android.content.SharedPreferences
import com.hisham.news.data.prefs.PreferenceManagerImpl
import org.koin.dsl.module

val persistenceDataModule = module {
    single {
        provideSharedPreference(get(), "News_prefs")
    }
    single {
        providePreferenceManager(get())
    }
}

fun provideSharedPreference(context: Context, preferenceName: String): SharedPreferences {
    return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
}

fun providePreferenceManager(preferences: SharedPreferences): com.hisham.news.data.prefs.PreferenceManager =
    PreferenceManagerImpl(preferences)