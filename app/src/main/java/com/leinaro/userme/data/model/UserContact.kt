package com.leinaro.userme.data.model

data class UserContact(
    val id: String = "",
    val profilePicture: String,
    val name: String,
    val email: String,
    val genre: String = "",
    val registerDate: String = "",
    val phone: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)