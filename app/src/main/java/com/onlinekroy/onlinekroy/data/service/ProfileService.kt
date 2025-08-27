package com.onlinekroy.onlinekroy.data.service

import android.net.Uri
import com.onlinekroy.onlinekroy.data.models.Product
import com.onlinekroy.onlinekroy.data.models.Profile
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask

interface ProfileService {

    fun uploadProfileImage(profileImageUri: Uri): UploadTask

    fun updateUser(user: Profile): Task<Void>

    fun getUserByUserID(userID: String): Task<QuerySnapshot>


}