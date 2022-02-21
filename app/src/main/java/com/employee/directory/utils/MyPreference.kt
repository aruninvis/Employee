package com.employee.directory.utils
import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context : Context){
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getLoadStatus(): Boolean {
        return prefs.getBoolean(DATA_LOADED, false)
    }
    fun setLoadStatus(status: Boolean) {
        prefs.edit().putBoolean(DATA_LOADED, status).apply()
    }
}