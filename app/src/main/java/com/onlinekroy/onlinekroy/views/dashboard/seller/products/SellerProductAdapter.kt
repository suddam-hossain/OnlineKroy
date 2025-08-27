package com.onlinekroy.onlinekroy.views.dashboard.seller.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.onlinekroy.onlinekroy.data.models.Product
import com.onlinekroy.onlinekroy.databinding.ItemSellerProductBinding

class SellerProductAdapter(val productList : List<Product>) : RecyclerView.Adapter<SellerProductAdapter.ProductViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        return ProductViewHolder(ItemSellerProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        productList[position].let {
            holder.binding.apply {
                txtProductName.text = it.name
                txtProductDescription.text = it.description
                txtProductStock.text = "Stock : ${it.amount}"
                txtProductPrice.text = "Price : ৳${it.price}"
                ivProduct.load(it.imageLink)
            }
        }


    }

    override fun getItemCount(): Int {

        return productList.size

    }

    class ProductViewHolder(val binding: ItemSellerProductBinding): RecyclerView.ViewHolder(binding.root)
}