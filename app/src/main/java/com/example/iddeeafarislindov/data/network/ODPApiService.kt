package com.example.iddeeafarislindov.data.network


import com.example.iddeeafarislindov.data.models.ApiResponse
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.data.models.died.DiedRequest
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ODPApiService {

    @POST("api/PersonsByRecordDate/list")
    suspend fun getPersonsByRecordDate(@Body request: PersonsRequest): ApiResponse<PersonRecord>

    @POST("api/IssuedIDCards/list")
    suspend fun getIssuedIdCards(@Body request: IdCardsRequest): ApiResponse<IssuedIdCard>

    @POST("api/DiedByRequestDate/list")
    suspend fun getDiedByRequestDate(@Body request: DiedRequest): ApiResponse<DiedRecord>

    @POST("api/RegisteredVehiclesNumbers/list")
    suspend fun getRegisteredVehicles(@Body request: VehiclesRequest): ApiResponse<RegisteredVehicle>









}
