package com.example.Bank.uiApp.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentProductDetailsBinding
import com.example.Bank.models.addcarts.AddCarts
import com.example.Bank.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductDetailsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private var image: String = ""
    private var name: String = ""
    private var price: String = ""
    private var productID: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        image = ProductDetailsFragmentArgs.fromBundle(requireArguments()).image
        name = ProductDetailsFragmentArgs.fromBundle(requireArguments()).nameCategory
        price = ProductDetailsFragmentArgs.fromBundle(requireArguments()).priceCategory
        productID = ProductDetailsFragmentArgs.fromBundle(requireArguments()).productID

        Glide.with(requireContext()).load(image).into(binding.imgDescription)
        binding.tvNameCategory.text = name
        binding.tvPrice.text = "Price : $price"

        binding.btnAddCart.setOnClickListener {
            addCarts()
        }


        return view
    }

    private fun addCarts() {
        viewModel.addCarts(productID).observe(viewLifecycleOwner, Observer  {addCarts ->

            val msg = addCarts?.message


            MaterialAlertDialogBuilder(requireContext()).setTitle("").setMessage(msg)
                .setPositiveButton("OK") { _, _ ->

                }.show()

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}