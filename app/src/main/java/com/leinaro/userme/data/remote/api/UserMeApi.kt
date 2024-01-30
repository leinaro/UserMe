package com.leinaro.userme.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

const val API = "/api/"

interface UserMeApi {
    @GET(API)
    suspend fun getUsers(
        @Query("results") size: Int,
        @Query("page") page: Int = 1,
        @Query("seed") seed: String = "9ddbbc1613d70f16",
    ): UserMeResponse
}

data class UserMeResponse(
    val results: List<UserContactResponse>,
    val info: InfoResponse,
)

data class InfoResponse(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
data class UserContactResponse(
    val gender: String,
    val name: NameResponse,
    val location: LocationResponse,
    val email: String,
    val registered: RegisteredResponse,
    val phone: String,
    val picture: PictureResponse,
    val id: IdResponse,
)

data class NameResponse(
    val first: String,
    val last: String,
)

data class LocationResponse(
    val coordinates: CoordinatesResponse,
)

data class CoordinatesResponse(
    val latitude: String,
    val longitude: String,
)

data class RegisteredResponse(
    val date: String,
)

data class PictureResponse(
    val large: String,
    val medium: String,
    val thumbnail: String,
)

data class IdResponse(
    val value: String?,
)