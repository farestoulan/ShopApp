package com.example.Bank.uiApp.carts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.adapter.AdapterCarts
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentCartsBinding
import com.example.Bank.models.carts.Carts
import com.google.android.material.snackbar.BaseTransientBottomBar
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartsFragment : Fragment() {
    private var _binding: FragmentCartsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartsBinding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_cartsFragment_to_categoriesFragment)
        }


        val call = RetrofitClient.instance?.api?.getCarts()
        call?.enqueue(object : Callback<Carts?> {
            override fun onResponse(
                call: Call<Carts?>,
                response: Response<Carts?>,
            ) {
                val adapterCarts = AdapterCarts(requireContext(), response.body()?.data?.cart_items)
                binding.recyclerCarts.adapter = adapterCarts
                val progressBar = binding.progressBarCarts
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<Carts?>, t: Throwable) {
                Toast.makeText(requireContext(),t.message,Toast.LENGTH_LONG).show()
            }
        })


        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}