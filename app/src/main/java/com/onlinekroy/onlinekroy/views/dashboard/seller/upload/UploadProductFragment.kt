package com.onlinekroy.onlinekroy.views.dashboard.seller.upload

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.onlinekroy.onlinekroy.base.BaseFragment
import com.onlinekroy.onlinekroy.core.DataState
import com.onlinekroy.onlinekroy.core.areAllPermissionsGranted
import com.onlinekroy.onlinekroy.core.extract
import com.onlinekroy.onlinekroy.core.requestPermissions
import com.onlinekroy.onlinekroy.data.models.Product
import com.onlinekroy.onlinekroy.databinding.FragmentUploadProductBinding
import com.onlinekroy.onlinekroy.views.dashboard.seller.SellerDashboard
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class UploadProductFragment : BaseFragment<FragmentUploadProductBinding>(
    FragmentUploadProductBinding::inflate
) {
    private val product : Product by lazy() {
        Product()
    }
    private val viewModel: UploadProductViewModel by viewModels ()
    override fun setListener() {

        permissionsRequest = getPermissionsRequest()

        with(binding) {
            ivProduct.setOnClickListener {
                requestPermissions(permissionsRequest,permissionList)
            }


            btnUploadProduct.setOnClickListener {

                val name = etProductName.extract()
                val price = etProductPrice.extract()
                val description = etProductDescription.extract()
                val amount = etProductAmount.extract()


                FirebaseAuth.getInstance().currentUser?.let {
                    product.apply {
                        this.productID = UUID.randomUUID().toString()
                        this.sellerID = it.uid
                        this.name = name
                        this.description = description
                        this.price = price.toDouble()
                        this.amount = amount.toInt()
                    }
                }



                uploadProduct(product)

              //  startActivity(Intent(requireContext(), SellerDashboard::class.java))
              //  requireActivity().finish()

            }
        }

    }

    private fun getPermissionsRequest(): ActivityResultLauncher<Array<String>> {

        return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            if (areAllPermissionsGranted(permissionList)){

                ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }
                Toast.makeText(requireContext(), "Ase", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Nai", Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun uploadProduct(product: Product) {

        viewModel.productUpload(product)
    }

    override fun allObserver() {

        viewModel.productUploadResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error-> {
                    loading.dismiss()
                }
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
                    loading.dismiss()
                }

            }
        }

    }

    companion object{
        private val permissionList = if (Build.VERSION.SDK_INT >=33){
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_MEDIA_IMAGES
            )
        }else{
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }
    private lateinit var permissionsRequest: ActivityResultLauncher<Array<String>>


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!
                binding.ivProduct.setImageURI(fileUri)
                product.imageLink = fileUri.toString()
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}