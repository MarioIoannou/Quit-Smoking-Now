package com.helpapp.quitsmoking.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CigaretteDao {

    //Cigarette

    @Query("SELECT * FROM Cigarette_table")
    fun getAll(): List<Cigarette>

    @Query("SELECT * FROM Cigarette_table WHERE date = strftime('%d', 'now')")
    fun getExpensesDay(): LiveData<List<Cigarette?>?>?

    @Query("SELECT * FROM Cigarette_table WHERE date BETWEEN :from AND :to")
    fun findCigaretteConsumption(from: Long, to: Long): List<Cigarette>

    @Delete
    fun deleteCigarette(cigarette: Cigarette)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCigarette(cigarette: Cigarette)

    @Query("SELECT * FROM Cigarette_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Cigarette>>

    @Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('now', '-24 Hours')")
    fun getTodayCigarettes(): Int

    @Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('-24 Hours', '-48 Hours')")
    fun getYesterdayCigarettes(): Int

    /*@Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('now', '-7 Days')")
    fun getWeeksCigarettes(): Int

    @Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('-7 Days', '-14 Days')")
    fun getLastWeeksCigarettes(): Int*/

    //Test
    /*@Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('now', '-5 Minutes')")
    fun getTodayCigarettes(): Int

    @Query("SELECT Count(*) FROM Cigarette_table WHERE  datetime(Date/1000,'unixepoch') >=datetime('-5 Minutes', '-10 Minutes')")
    fun getYesterdayCigarettes(): Int*/

}


