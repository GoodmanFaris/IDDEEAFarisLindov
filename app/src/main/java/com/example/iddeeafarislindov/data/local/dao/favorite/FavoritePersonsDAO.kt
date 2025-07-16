package com.example.iddeeafarislindov.data.local.dao.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoritePersonsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePersonsDao {
    @Query("SELECT * FROM favorite_persons")
    fun getAllFavorites(): Flow<List<FavoritePersonsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavoritePersonsEntity)

    @Delete
    suspend fun delete(fav: FavoritePersonsEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_persons WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}
