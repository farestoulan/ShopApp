package com.example.Bank.uiApp.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.Bank.adapter.AdapterProduct
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentProductBinding
import com.example.Bank.models.products.ListOfProduct
import com.example.Bank.models.products.Products
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductFragment : Fragment() {
    var categoryID: Int = 0
    private var _binding: FragmentProductBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root

        categoryID = ProductFragmentArgs.fromBundle(requireArguments()).categoryID


        val call = RetrofitClient.instance?.api?.getListOfProducts(categoryID)
        call?.enqueue(object : Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                if (response.isSuccessful) {

                    val adapterProduct =
                        AdapterProduct(requireContext(), response.body()!!.data.result)


                    // Create a grid layout with two columns
                    val  layoutManager = GridLayoutManager(requireContext(), 2)


                    val recyclerView = binding.recyclerViewOfProducts
                    recyclerView.adapter = adapterProduct
                    recyclerView.layoutManager = layoutManager
                    recyclerView.setHasFixedSize(true)
                    val progressBar = binding.progressBarProducts
                    progressBar.visibility =View.GONE



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

                } else {
                    MaterialAlertDialogBuilder(requireContext()).setTitle("انتبه يا جدع ")
                        .setMessage(response.body()?.message).setPositiveButton("OK") { _, _ ->
                        }.show()
                }
            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {
                MaterialAlertDialogBuilder(requireContext()).setTitle("انتبه يا جدع ")
                    .setMessage(t.message).setPositiveButton("OK") { _, _ ->
                    }.show()
            }
        })


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}