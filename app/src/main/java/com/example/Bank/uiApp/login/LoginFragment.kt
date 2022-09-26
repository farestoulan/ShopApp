package com.example.Bank.uiApp.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentLoginBinding
import com.example.Bank.models.userdata.LoginRespons
import com.example.Bank.storage.SharedPrefManager
import com.example.Bank.viewModel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            userLogIn()
        }

        binding.btnRegLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        return view

    }

    fun userLogIn() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        viewModel.userLogin(email, password).observe(viewLifecycleOwner) { loginResponse ->
            if (loginResponse?.status == true) {
                findNavController().navigate(R.id.action_loginFragment_to_categoriesFragment)
            } else {
                val loginMassageResponse = loginResponse?.message

                MaterialAlertDialogBuilder(requireContext()).setTitle("")
                    .setMessage(loginMassageResponse)
                    .setPositiveButton("OK") { _, _ ->
                    }.show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}