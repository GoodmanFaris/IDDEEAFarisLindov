package com.example.iddeeafarislindov.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iddeeafarislindov.data.local.entities.DiedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiedDao {
    @Query("SELECT * FROM died_records")
    fun getAll(): Flow<List<DiedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<DiedEntity>)

    @Query("DELETE FROM died_records")
    suspend fun clearAll()
}