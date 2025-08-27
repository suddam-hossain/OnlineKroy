package com.onlinekroy.onlinekroy.views.dashboard.seller.profile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onlinekroy.onlinekroy.core.DataState
import com.onlinekroy.onlinekroy.data.models.Product
import com.onlinekroy.onlinekroy.data.models.Profile
import com.onlinekroy.onlinekroy.data.repository.ProfileRepository
import com.onlinekroy.onlinekroy.data.repository.SellerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SellerProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
): ViewModel(){

   private val _profileUpdateResponse = MutableLiveData<DataState<String>>()

    val profileUpdateResponse: LiveData<DataState<String>> = _profileUpdateResponse



    fun updateProfile(user: Profile, hashLocalImageUrl : Boolean){

        _profileUpdateResponse.postValue(DataState.Loading())

        if (hashLocalImageUrl){
            val imageUri: Uri? = user.userImage?.toUri()

            imageUri?.let {
                repo.uploadProfileImage(it).addOnSuccessListener { snapshot->

                    snapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { url->
                        user.userImage = url.toString()
                        repo.updateUser(user).addOnSuccessListener {
                            _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated user Profile Successfully!"))
                        }.addOnFailureListener {
                            _profileUpdateResponse.postValue(DataState.Error("${it.message}"))
                        }


                    }

                }.addOnFailureListener {
                    _profileUpdateResponse.postValue(DataState.Error("Image Uploaded fail!"))
                }
            }

        }else{
            repo.updateUser(user).addOnSuccessListener {
                _profileUpdateResponse.postValue(DataState.Success("Uploaded and Updated user Profile Successfully!"))
            }.addOnFailureListener {
                _profileUpdateResponse.postValue(DataState.Error("${it.message}"))
            }
        }





    }

    private val _logedInUserResponse = MutableLiveData<DataState<Profile>>()

    val logedInUserResponse: LiveData<DataState<Profile>>
    get() = _logedInUserResponse

    fun getUserByUserID(userID: String){
        _logedInUserResponse.postValue(DataState.Loading())
        repo.getUserByUserID(userID).addOnSuccessListener { value->

            _logedInUserResponse.postValue(DataState.Success(
                value.documents[0].toObject(
                    Profile::class.java
                )
            ))
        }
    }


}