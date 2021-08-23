package com.takendra.retailtstore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProductItem::class], version = 1, exportSchema = false)
abstract class CartDataBase : RoomDatabase() {
    abstract fun cartDatabaseDao(): CartDatabaseDao

    companion object {

        private var INSTANCE: CartDataBase? = null

        fun getInstance(context: Context): CartDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDataBase::class.java,
                        "cart_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}