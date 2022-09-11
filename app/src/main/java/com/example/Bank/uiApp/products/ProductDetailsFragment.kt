package com.example.Bank.uiApp.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentProductDetailsBinding
import com.example.Bank.models.addcarts.AddCarts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private var image: String = ""
    private var name: String = ""
    private var price: String = ""
    private var productID:Int = 0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        image = ProductDetailsFragmentArgs.fromBundle(requireArguments()).image
        name = ProductDetailsFragmentArgs.fromBundle(requireArguments()).nameCategory
        price = ProductDetailsFragmentArgs.fromBundle(requireArguments()).priceCategory
        productID =ProductDetailsFragmentArgs.fromBundle(requireArguments()).productID

        Glide.with(requireContext()).load(image).into( binding.imgDescription)
        binding.tvNameCategory.text = name
        binding.tvPrice.text ="Price : $price"

        binding.btnAddCart.setOnClickListener {
            addCarts()
        }


        return view
    }

    private fun addCarts(){
        val call = RetrofitClient.instance?.api?.addCarts(productID)
        call?.enqueue(object :Callback<AddCarts?>{
            override fun onResponse(call: Call<AddCarts?>, response: Response<AddCarts?>) {
                if (response.isSuccessful){

                    Toast.makeText(requireContext(),response.body()?.message,Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<AddCarts?>, t: Throwable) {

                Toast.makeText(requireContext(),t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}