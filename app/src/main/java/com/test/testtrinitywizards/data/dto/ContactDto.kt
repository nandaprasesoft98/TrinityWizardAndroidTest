package com.test.testtrinitywizards.data.dto

import com.google.gson.annotations.SerializedName
import com.test.testtrinitywizards.domain.model.Contact

data class ContactDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("dob")
    val dob: String?
) {
    fun toDomainModel(): Contact {
        return Contact(
            id = this.id,
            firstName = this.firstName ?: "",
            lastName = this.lastName ?: "",
            email = this.email ?: "",
            dob = this.dob ?: ""
        )
    }
}