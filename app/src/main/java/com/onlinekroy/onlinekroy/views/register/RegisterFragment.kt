package com.onlinekroy.onlinekroy.views.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onlinekroy.onlinekroy.R
import com.onlinekroy.onlinekroy.base.BaseFragment
import com.onlinekroy.onlinekroy.core.DataState
import com.onlinekroy.onlinekroy.data.models.UserRegistration
import com.onlinekroy.onlinekroy.databinding.FragmentRegisterBinding
import com.onlinekroy.onlinekroy.isEmpty
import com.onlinekroy.onlinekroy.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {



    private val viewModel: RegistrationViewModel by viewModels ()



    override fun setListener() {
        with(binding){
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if(!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()){

                    val user = UserRegistration(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Marchent",
                        userID = ""
                    )
                    viewModel.userRegistration(user)

                }

            }
        }
    }

    override fun allObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    Toast.makeText(context, "Created User: ${it.data}", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(requireContext(), SellerDashboard::class.java))

                }
            }
        }
    }



}