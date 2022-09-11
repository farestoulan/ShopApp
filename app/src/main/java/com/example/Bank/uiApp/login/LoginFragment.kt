package com.example.Bank.uiApp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentLoginBinding
import com.example.Bank.models.userdata.LoginRespons
import com.example.Bank.storage.SharedPrefManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnLogin.setOnClickListener {
            val email1 = binding.etEmail.text.toString().trim()
            val password1 = binding.etPassword.text.toString().trim()


            val call = RetrofitClient
                .instance?.api?.userLogin(email1, password1)

            call?.enqueue(object : Callback<LoginRespons?> {
                override fun onResponse(
                    call: Call<LoginRespons?>,
                    response: Response<LoginRespons?>,
                ) {
                    val loginResponse = response.body()
                    if (loginResponse!!.status) {
                        SharedPrefManager.getInstance(requireContext())
                            ?.saveUser(response.body()!!.data)
                        findNavController().navigate(R.id.action_loginFragment_to_categoriesFragment)

                    } else {
                        MaterialAlertDialogBuilder(requireContext()).setTitle("خد بالك يا جدع")
                            .setMessage(loginResponse.message).setPositiveButton("OK") { _, _ ->
                            }.show()
                    }

                }

                override fun onFailure(call: Call<LoginRespons?>, t: Throwable) {

                }


            })
        }

        binding.btnRegLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }




        return view

    }


}