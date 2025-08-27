package com.onlinekroy.onlinekroy.views.starter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.onlinekroy.onlinekroy.R
import com.onlinekroy.onlinekroy.base.BaseFragment
import com.onlinekroy.onlinekroy.databinding.FragmentStartBinding
import com.onlinekroy.onlinekroy.views.dashboard.seller.SellerDashboard

class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    override fun setListener() {

        setUpAutoLogin()
        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
        }
    }

    private fun setUpAutoLogin(){
        FirebaseAuth.getInstance().currentUser?.let {
            startActivity(Intent(requireContext(), SellerDashboard::class.java))
            requireActivity().finish()
        }
    }

    override fun allObserver() {
    }

}