package com.example.Bank.uiApp.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.adapter.AdapterCategory
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentCategoriesBinding
import com.example.Bank.models.categories.Categories
import com.example.Bank.models.categories.ListOfCategories
import com.example.Bank.repository.Repository
import com.example.Bank.uiApp.filter
import com.example.Bank.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
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
        val progressBar = binding.progressBar
        progressBar.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        if (viewModel.myResponseGetListOfCategories.value == null)
            viewModel.getListOfCategories()

        viewModel.myResponseGetListOfCategories.observe(viewLifecycleOwner) { categories ->
            if (categories != null) {


                progressBar.visibility = View.GONE

                val dataResults = categories?.data?.results

                filter(dataResults) { it.name == "mohamed" }.let { categories ->

                }
                val adapterCategory = AdapterCategory(
                    requireContext(),
                    dataResults!!
                )
                val recyclerView = binding.recyclerViewCategories
                recyclerView.adapter = adapterCategory

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
                }
                )
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}