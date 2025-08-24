package com.onlinekroy.onlinekroy.data.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.onlinekroy.onlinekroy.data.models.UserLogin
import com.onlinekroy.onlinekroy.data.models.UserRegistration

interface AuthService {

    fun userRegistration(user: UserRegistration):Task<AuthResult>
    fun userLogin(user: UserLogin): Task<AuthResult>
    fun createUser(user: UserRegistration): Task<Void>

}