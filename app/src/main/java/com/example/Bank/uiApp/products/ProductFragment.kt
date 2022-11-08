package com.example.Bank.uiApp.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Bank.adapter.AdapterProduct
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentProductBinding
import com.example.Bank.models.products.ListOfProduct
import com.example.Bank.models.products.Products
import com.example.Bank.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {
    private var categoryID: Int = 0
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root

        categoryID = ProductFragmentArgs.fromBundle(requireArguments()).categoryID

        val progressBar = binding.progressBarProducts
        progressBar.visibility = View.VISIBLE
        val recyclerView = binding.recyclerViewOfProducts
        val gridLayoutManagerWithTwoColumns = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManagerWithTwoColumns
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getListOfProducts(categoryID)

        viewModel.myResponseGetListOfProducts.observe(viewLifecycleOwner) { products ->

            if (products != null) {

                val dataResults = products.data.result
                val adapterProduct = AdapterProduct(requireContext(), dataResults)

                recyclerView.adapter = adapterProduct

                progressBar.visibility = View.GONE

                adapterProduct.setClickListener(object : AdapterProduct.ItemClickListener {
                    override fun onItemClick(
                        view: View?,
                        position: Int,
                        data: List<ListOfProduct?>?,
                    ) {
                        val action =
                            ProductFragmentDirections.actionProductFragmentToProductDetailsFragment(
                                data?.get(position)!!.image,
                                data[position]?.name.toString(),
                                data[position]?.price.toString(),
                                data[position]?.id!!

                            )
                        findNavController().navigate(action)
                    }
                })

            }
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}