package com.example.quitsmoking.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quitsmoking.Converters
import com.example.quitsmoking.fragments.MainFragment

@TypeConverters(Converters::class)
@Database(entities = [Cigarette::class],version = 1,exportSchema = false)
abstract class CigaretteDatabase: RoomDatabase() {

    abstract fun  cigaretteDao(): CigaretteDao
    companion object{
        @Volatile
        private var INSTANCE: CigaretteDatabase? = null

        fun getDatabase(context: MainFragment): CigaretteDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instane = Room.databaseBuilder(
                    context.requireContext(),
                    CigaretteDatabase::class.java,
                    "Cigarette_table"
                ).build()
                INSTANCE = instane
                return instane
            }
        }
    }
}