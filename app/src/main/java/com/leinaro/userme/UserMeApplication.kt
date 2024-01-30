package com.leinaro.userme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class UserMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val locale = Locale.getAvailableLocales().find { it.country == "CO" } ?: Locale.US
        baseContext.resources.configuration.setLocale(locale)
    }
}