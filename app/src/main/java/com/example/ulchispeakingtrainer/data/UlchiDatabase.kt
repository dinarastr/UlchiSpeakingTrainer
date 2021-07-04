package com.example.ulchispeakingtrainer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UlchiText::class, UlchiPhrase::class, NewPhrase::class, Theme::class], version = 1, exportSchema = false)
abstract class UlchiDatabase: RoomDatabase() {

    abstract fun ThemeDao(): ThemeDao

    companion object {

        @Volatile
        private var INSTANCE: UlchiDatabase? = null

        fun getDatabase(context: Context): UlchiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UlchiDatabase::class.java,
                    "ulchidb.db"
                ).createFromAsset("ulchidb.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}