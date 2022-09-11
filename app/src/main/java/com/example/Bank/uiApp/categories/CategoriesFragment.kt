package com.example.Bank.uiApp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.adapter.AdapterCategory
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentCategoriesBinding
import com.example.Bank.models.categories.Categories
import com.example.Bank.models.categories.ListOfCategories
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            MaterialAlertDialogBuilder(requireContext()).setTitle("انتبيه لو سمحت")
                .setMessage("Do You want Logout")
                .setPositiveButton("OK") { _, _ ->
                    findNavController().navigate(R.id.action_categoriesFragment_to_loginFragment)

                }.setNegativeButton("No") { _, _ ->

                }.show()
        }
        val call = RetrofitClient
            .instance?.api?.getListOfCategories()
        call?.enqueue(object : Callback<Categories?> {
            override fun onResponse(
                call: Call<Categories?>,
                response: Response<Categories?>,
            ) {
                if (response.code() == 200) {
                    val adapterCategory = AdapterCategory(requireContext(),
                        response.body()!!.data.results)

                    val recyclerView = binding.recyclerViewCategories

                    recyclerView.adapter = adapterCategory
                    val progressBar = binding.progressBar
                    progressBar.visibility = View.GONE

                    adapterCategory.setClickListener(object : AdapterCategory.ItemClickListener {
                        override fun onItemClick(
                            view: View?,
                            position: Int,
                            data: List<ListOfCategories>,
                        ) {

                            val action =
                                CategoriesFragmentDirections.actionCategoriesFragmentToProductFragment2(
                                    data[position].id
                                )
                            findNavController().navigate(action)

                        }
                    })
                } else {
                    MaterialAlertDialogBuilder(requireContext()).setTitle("انتبه  ")
                        .setMessage(response.message()).setPositiveButton("OK") { _, _ ->
                        }.show()
                }

            }

            override fun onFailure(call: Call<Categories?>, t: Throwable) {
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