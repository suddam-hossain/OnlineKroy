package com.onlinekroy.onlinekroy.views.shop

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onlinekroy.onlinekroy.R
import com.onlinekroy.onlinekroy.base.BaseFragment
import com.onlinekroy.onlinekroy.databinding.FragmentShopBinding
import com.google.firebase.auth.FirebaseAuth
import com.onlinekroy.onlinekroy.core.DataState
import com.onlinekroy.onlinekroy.data.models.Product
import com.onlinekroy.onlinekroy.views.dashboard.seller.products.ProductViewModel
import com.onlinekroy.onlinekroy.views.dashboard.seller.products.SellerProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding>(FragmentShopBinding::inflate) {


    private val viewModel : ShopViewModel by viewModels()
    override fun setListener() {


        with(binding){
            btnMerchant.setOnClickListener {
                findNavController().navigate(R.id.action_shopFragment_to_startFragment)
            }

        }
    }





    override fun allObserver() {

        viewModel.productResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                }
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success->
                {
                    it.data?.let { it1->
                        setDataToRV(it1)
                    }
                    loading.dismiss()
                }
            }
        }



    }

    private fun setDataToRV(it: List<Product>) {

        binding.rvProducts.adapter = ShopProductAdapter(it)

    }




}