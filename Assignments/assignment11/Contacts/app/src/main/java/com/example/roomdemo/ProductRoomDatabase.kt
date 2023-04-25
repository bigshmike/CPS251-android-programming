package com.example.roomdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Contact::class)], version = 1)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun contactDAO(): ContactDAO

    companion object {
        private var INSTANCE: ProductRoomDatabase? = null
        // Retrieves the database instance, creating it if it doesn't exist
        internal fun getDatabase(context: Context): ProductRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Builds a new instance of the database
                        INSTANCE =
                            Room.databaseBuilder(
                                context.applicationContext,
                                ProductRoomDatabase::class.java,
                                "contacts_database"
                            ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}