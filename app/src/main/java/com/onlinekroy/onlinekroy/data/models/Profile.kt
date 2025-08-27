package com.onlinekroy.onlinekroy.data.models

data class Profile(
    var name:String="",
    var email:String="",
    var password:String="",
    var userType: String="",
    var userID: String="",
    var userImage: String?=null,
    var shopName: String?=null

)
fun Profile.toMap(): Map<String, Any?> {
    return mapOf(
        "name" to name,
        "email" to email,
        "password" to password,
        "userType" to userType,
        "userID" to userID,
        "userImage" to userImage,
        "shopName" to shopName
    )
}