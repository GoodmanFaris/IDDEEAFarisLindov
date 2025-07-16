package com.example.iddeeafarislindov.data.local.dao.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteVehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteVehicleDao {
    @Query("SELECT * FROM favorite_vehicles")
    fun getAllFavorites(): Flow<List<FavoriteVehicleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fav: FavoriteVehicleEntity)

    @Delete
    suspend fun delete(fav: FavoriteVehicleEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_vehicles WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}
