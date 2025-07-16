package com.example.iddeeafarislindov


import android.app.Application
import androidx.room.Room
import com.example.iddeeafarislindov.data.local.AppDatabase

class App : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    companion object {
        lateinit var instance: App
            private set
    }

    init {
        instance = this
    }
}
