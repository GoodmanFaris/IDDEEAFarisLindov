package com.example.iddeeafarislindov.data.repository

import com.example.iddeeafarislindov.data.local.AppDatabase
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteDiedDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteIdCardDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoritePersonsDao
import com.example.iddeeafarislindov.data.local.dao.favorite.FavoriteVehicleDao
import com.example.iddeeafarislindov.data.local.mapper.favorite.toFavoriteEntity
import com.example.iddeeafarislindov.data.local.mapper.toEntity
import com.example.iddeeafarislindov.data.local.mapper.toRecord
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.data.models.died.DiedRequest
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import com.example.iddeeafarislindov.data.network.ApiClient
import com.example.iddeeafarislindov.data.local.mapper.favorite.toRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppRepository(private val db: AppDatabase)  {
    private val personDao = db.personDao()


    suspend fun getPersonsByRecordDate(request: PersonsRequest): List<PersonRecord> {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.odpApiService.getPersonsByRecordDate(request)
                val data = response.result

                personDao.clearAll()
                personDao.insertAll(data.map { it.toEntity() })
                data
            } catch (e: Exception) {
                personDao.getAllPersons().first().map { it.toRecord() }
            }
        }
    }

    suspend fun getIssuedIdCards(request: IdCardsRequest): List<IssuedIdCard> {
        val dao = db.IdCardDao()
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.odpApiService.getIssuedIdCards(request)
                val data = response.result

                dao.clearAll()
                dao.insertAll(data.map { it.toEntity() })

                data
            } catch (e: Exception) {
                dao.getAll().first().map { it.toRecord() }
            }
        }
    }


    suspend fun getDied(request: DiedRequest): List<DiedRecord> {
        val dao = db.DiedDao()
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.odpApiService.getDiedByRequestDate(request)
                val data = response.result

                dao.clearAll()
                dao.insertAll(data.map { it.toEntity() })

                data
            } catch (e: Exception) {
                dao.getAll().first().map { it.toRecord() }
            }
        }
    }

    suspend fun getRegisteredVehicles(request: VehiclesRequest): List<RegisteredVehicle> {
        val dao = db.vehicleDao()
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.odpApiService.getRegisteredVehicles(request)
                val data = response.result

                dao.clearAll()
                dao.insertAll(data.map { it.toEntity() })

                data
            } catch (e: Exception) {
                dao.getAll().first().map { it.toRecord() }
            }
        }
    }


}

class FavoriteVehicleRepository(private val dao: FavoriteVehicleDao) {

    val favorites: Flow<List<RegisteredVehicle>> = dao.getAllFavorites()
        .map { list -> list.map { it.toRecord() } }

    suspend fun addToFavorites(vehicle: RegisteredVehicle) {
        dao.insert(vehicle.toFavoriteEntity())
    }

    suspend fun removeFromFavorites(vehicle: RegisteredVehicle) {
        dao.delete(vehicle.toFavoriteEntity())
    }

    suspend fun isFavorite(vehicle: RegisteredVehicle): Boolean {
        val id = "${vehicle.registrationPlace}-${vehicle.year}-${vehicle.month}"
        return dao.isFavorite(id)
    }
}

class FavoriteDiedRepository(private val dao: FavoriteDiedDao) {

    val favorites: Flow<List<DiedRecord>> = dao.getAllFavorites()
        .map { list -> list.map { it.toRecord() } }

    suspend fun addToFavorites(died: DiedRecord) {
        dao.insert(died.toFavoriteEntity())
    }

    suspend fun removeFromFavorites(died: DiedRecord) {
        dao.delete(died.toFavoriteEntity())
    }

    suspend fun isFavorite(died: DiedRecord): Boolean {
        val id = "${died.institution}-${died.year}-${died.month}"
        return dao.isFavorite(id)
    }
}

class FavoriteIdCardRepository(private val dao: FavoriteIdCardDao) {

    val favorites: Flow<List<IssuedIdCard>> = dao.getAllFavorites()
        .map { list -> list.map { it.toRecord() } }

    suspend fun addToFavorites(card: IssuedIdCard) {
        dao.insert(card.toFavoriteEntity())
    }

    suspend fun removeFromFavorites(card: IssuedIdCard) {
        dao.delete(card.toFavoriteEntity())
    }

    suspend fun isFavorite(card: IssuedIdCard): Boolean {
        val id = "${card.institution}-${card.year}-${card.month}"
        return dao.isFavorite(id)
    }
}

class FavoritePersonsRepository(private val dao: FavoritePersonsDao) {

    val favorites: Flow<List<PersonRecord>> = dao.getAllFavorites()
        .map { list -> list.map { it.toRecord() } }

    suspend fun addToFavorites(person: PersonRecord) {
        dao.insert(person.toFavoriteEntity())
    }

    suspend fun removeFromFavorites(person: PersonRecord) {
        dao.delete(person.toFavoriteEntity())
    }

    suspend fun isFavorite(person: PersonRecord): Boolean {
        val id = "${person.institution}-${person.year}-${person.month}"
        return dao.isFavorite(id)
    }
}

