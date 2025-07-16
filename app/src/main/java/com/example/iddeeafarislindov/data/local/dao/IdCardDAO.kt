package com.example.iddeeafarislindov.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iddeeafarislindov.data.local.entities.IdCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IdCardDao {
    @Query("SELECT * FROM id_cards")
    fun getAll(): Flow<List<IdCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<IdCardEntity>)

    @Query("DELETE FROM id_cards")
    suspend fun clearAll()
}