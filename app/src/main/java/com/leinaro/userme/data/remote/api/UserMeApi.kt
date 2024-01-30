package com.leinaro.userme.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

const val API = "/api/"

interface UserMeApi {
    @GET(API)
    suspend fun getUsers(@Query("results") results:Int): UserMeResponse
}

data class UserMeResponse(
    val results: List<UserContactResponse>,
    //val page: Int,
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
    val street: StreetResponse,
    val city: String,
    val state: String,
    val country: String,
    val coordinates: CoordinatesResponse,
    val timezone: TimezoneResponse,
)

data class StreetResponse(
    val number: Int,
    val name: String,
)

data class CoordinatesResponse(
    val latitude: String,
    val longitude: String,
)

data class TimezoneResponse(
    val offset: String,
    val description: String,
)

data class RegisteredResponse(
    val date: String,
    val age: Int,
)

data class PictureResponse(
    val large: String,
    val medium: String,
    val thumbnail: String,
)

data class IdResponse(
    val name: String,
    val value: String?,
)