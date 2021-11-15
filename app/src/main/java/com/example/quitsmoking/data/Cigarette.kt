package com.example.quitsmoking.data

import androidx.room.*
import com.example.quitsmoking.Converters
import org.joda.time.DateTime
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = "Cigarettes_table")
data class Cigarette(
    @PrimaryKey
        val id: Int,

    @ColumnInfo(name = "Date")
        val date: Date?
    )