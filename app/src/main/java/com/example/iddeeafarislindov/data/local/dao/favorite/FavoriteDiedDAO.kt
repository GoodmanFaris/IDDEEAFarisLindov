package com.example.iddeeafarislindov.data.local.dao.favorite


import androidx.room.*
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteDiedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDiedDao {
    @Query("SELECT * FROM favorite_died")
    fun getAllFavorites(): Flow<List<FavoriteDiedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavoriteDiedEntity)

    @Delete
    suspend fun delete(fav: FavoriteDiedEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_died WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}
