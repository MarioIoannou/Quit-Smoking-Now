package com.example.quitsmoking.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface CigaretteDao {

    @Query("SELECT * FROM cigarettes_table")
    fun getAll(): List<Cigarette>

    @Query("SELECT * FROM cigarettes_table WHERE date = strftime('%d', 'now')")
    fun getExpensesDay(): LiveData<List<Cigarette?>?>?

    @Transaction
    fun findUsersBornBetweenDates(from: Date, to: Date): List<Cigarette> {
        //convert dates to long
        return findUsersBornBetweenDates(from, to)
    }

    @Query("SELECT * FROM cigarettes_table WHERE date BETWEEN :from AND :to")
    fun findCigaretteConsumption(from: Long, to: Long): List<Cigarette>

    @Delete
    suspend fun deleteCigarette(cigarette: Cigarette)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCigarette(cigarette: Cigarette)

    @Query("SELECT * FROM Cigarettes_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cigarette>>

}
