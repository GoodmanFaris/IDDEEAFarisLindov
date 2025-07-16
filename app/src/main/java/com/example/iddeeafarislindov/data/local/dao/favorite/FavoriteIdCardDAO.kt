package com.example.iddeeafarislindov.data.local.dao.favorite

import androidx.room.*
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteIdCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteIdCardDao {
    @Query("SELECT * FROM favorite_id_cards")
    fun getAllFavorites(): Flow<List<FavoriteIdCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavoriteIdCardEntity)

    @Delete
    suspend fun delete(fav: FavoriteIdCardEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_id_cards WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}