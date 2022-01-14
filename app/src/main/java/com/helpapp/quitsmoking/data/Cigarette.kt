package com.helpapp.quitsmoking.data

import androidx.room.*
import androidx.room.TypeConverters
import com.helpapp.quitsmoking.data.Converters
import java.util.*

@Entity(tableName = "Cigarette_table")
data class Cigarette(
    @PrimaryKey
        val id: Int,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "Date")
        val date: Date? = Date(System.currentTimeMillis())
    )