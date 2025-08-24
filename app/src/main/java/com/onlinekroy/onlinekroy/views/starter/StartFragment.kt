package com.onlinekroy.onlinekroy.views.starter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.onlinekroy.onlinekroy.R
import com.onlinekroy.onlinekroy.base.BaseFragment
import com.onlinekroy.onlinekroy.databinding.FragmentStartBinding

class StartFragment : BaseFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    override fun setListener() {
        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
        }
    }

    override fun allObserver() {
    }

}