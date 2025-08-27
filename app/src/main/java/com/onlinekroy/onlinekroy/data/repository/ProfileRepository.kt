package com.onlinekroy.onlinekroy.data.repository

import android.net.Uri
import com.onlinekroy.onlinekroy.core.Nodes
import com.onlinekroy.onlinekroy.data.models.Profile
import com.onlinekroy.onlinekroy.data.models.toMap
import com.onlinekroy.onlinekroy.data.service.ProfileService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) : ProfileService {
    override fun uploadProfileImage(profileImageUri: Uri): UploadTask {
        val storage: StorageReference = storageRef.child(Nodes.PROFILE).child("PRF_${System.currentTimeMillis()}")

        return storage.putFile(profileImageUri)

    }

    override fun updateUser(user: Profile): Task<Void> {
        return db.collection(Nodes.USER).document(user.userID).update(user.toMap())
    }

    override fun getUserByUserID(userID: String): Task<QuerySnapshot> {
        return  db.collection(Nodes.USER).whereEqualTo(Nodes.USERID, userID).get()
    }
}