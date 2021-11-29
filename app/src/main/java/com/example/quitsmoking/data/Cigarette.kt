package com.example.quitsmoking.data

import androidx.room.*
import androidx.room.TypeConverters
import com.example.quitsmoking.data.Converters
import java.util.*

@Entity(tableName = "Cigarettes_table")
data class Cigarette(
    @PrimaryKey
        val id: Int,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "Date")
        val date: Date? = Date(System.currentTimeMillis())
    )