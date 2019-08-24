package com.zhaku.detailing

data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<Student>)

data class globalUser(
    val available: Boolean,
    val ed_field: List<EdField>,
    val id: Int,
    val info: String,
    val location: Location,
    val max_amount: Int,
    val min_amount: Int,
    val phone_number: String,
    val preferredPlace: String,
    val profile_photos: List<ProfilePhoto>,
    val students: List<Student>,
    val type: String,
    val user: User
)

data class Student(
    val available: Boolean,
    val ed_field: List<EdField>,
    val id: Int,
    val info: String,
    val location: Location,
    val phone_number: String,
    val preferredPlace: String,
    val profile_photos: List<ProfilePhoto>,
    val sex: String,
    val type: String,
    val user: User
)

data class EducationCenter(
    val available: Boolean,
    val ed_field: List<EdField>,
    val id: Int,
    val info: String,
    val location: Location,
    val max_amount: Int,
    val min_amount: Int,
    val phone_number: String,
    val preferredPlace: String,
    val profile_photos: List<ProfilePhoto>,
    val students: List<Student>,
    val type: String,
    val user: User
)

data class ProfilePhoto(
    val name: String,
    val picture: String
)

data class User(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val username: String
)

data class Location(
    val city: String,
    val id: Int,
    val street: String
)

data class EdField(
    val id: Int,
    val name: String
)