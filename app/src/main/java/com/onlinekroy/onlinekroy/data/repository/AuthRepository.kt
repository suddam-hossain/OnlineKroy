package com.onlinekroy.onlinekroy.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.onlinekroy.onlinekroy.core.Nodes
import com.onlinekroy.onlinekroy.data.models.UserLogin
import com.onlinekroy.onlinekroy.data.models.UserRegistration
import com.onlinekroy.onlinekroy.data.service.AuthService
import jakarta.inject.Inject

class AuthRepository @Inject constructor(

    private  val jAuth: FirebaseAuth,
    private  val db: FirebaseFirestore


): AuthService {

    override fun userRegistration(user: UserRegistration) : Task<AuthResult> {

        return jAuth.createUserWithEmailAndPassword(user.email,user.password)
    }

    override fun userLogin(user: UserLogin) :  Task<AuthResult>  {
        return jAuth.signInWithEmailAndPassword(user.email, user.password)

    }

    override fun createUser(user: UserRegistration): Task<Void> {
        return  db.collection(Nodes.USER).document(user.userID).set(user)
    }


}