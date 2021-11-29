package com.example.quitsmoking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(entities = [Cigarette::class],version = 1,exportSchema = false)
abstract class CigaretteDatabase: RoomDatabase() {

    abstract fun  cigaretteDao(): CigaretteDao
    companion object{
        @Volatile
        private var INSTANCE: CigaretteDatabase? = null

        fun getDatabase(context: Context): CigaretteDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    CigaretteDatabase::class.java,
                    "Cigarette_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}