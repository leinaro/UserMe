package com.leinaro.userme.data.mapper

import com.leinaro.userme.data.model.UserContact
import com.leinaro.userme.data.remote.api.UserContactResponse
import java.text.SimpleDateFormat
import java.util.Locale

fun UserContactResponse.toDomain() : UserContact {
    val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val targetFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val formattedDate = try {
        originalFormat.parse(this.registered.date)?.let { date ->
            targetFormat.format(date)
        }
    } catch (e:Exception){
        this.registered.date
    }.orEmpty()

    return UserContact(
        id = this.id.value.orEmpty(),
        name = "${this.name.first} ${this.name.last}",
        email = this.email,
        profilePicture = this.picture.large,
        genre = this.gender,
        registerDate = formattedDate,
        phone = this.phone,
    )
}