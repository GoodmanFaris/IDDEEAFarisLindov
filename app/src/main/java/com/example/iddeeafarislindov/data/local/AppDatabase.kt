package com.example.iddeeafarislindov.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iddeeafarislindov.data.local.dao.DiedDao
import com.example.iddeeafarislindov.data.local.dao.IdCardDao
import com.example.iddeeafarislindov.data.local.dao.PersonDao
import com.example.iddeeafarislindov.data.local.dao.VehicleDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteDiedDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteIdCardDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoritePersonsDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteVehicleDao
import com.example.iddeeafarislindov.data.local.entities.DiedEntity
import com.example.iddeeafarislindov.data.local.entities.IdCardEntity
import com.example.iddeeafarislindov.data.local.entities.PersonEntity
import com.example.iddeeafarislindov.data.local.entities.VehicleEntity
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteDiedEntity
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteIdCardEntity
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoritePersonsEntity
import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteVehicleEntity

@Database(
    entities = [
        PersonEntity::class,
        IdCardEntity::class,
        DiedEntity::class,
        VehicleEntity::class,
        FavoriteVehicleEntity::class,
        FavoriteDiedEntity::class,
        FavoriteIdCardEntity::class,
        FavoritePersonsEntity::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun IdCardDao(): IdCardDao
    abstract fun DiedDao(): DiedDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun favoriteVehicleDao(): FavoriteVehicleDao
    abstract fun favoriteDiedDao(): FavoriteDiedDao
    abstract fun favoriteIdCardDao(): FavoriteIdCardDao
    abstract fun favoritePersonsDao(): FavoritePersonsDao
}
