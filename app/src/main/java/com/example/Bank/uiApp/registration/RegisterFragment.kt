package com.example.Bank.uiApp.registration
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.Bank.R
import com.example.Bank.api.RetrofitClient
import com.example.Bank.databinding.FragmentRegisterBinding
import com.example.Bank.models.userdata.LoginRespons
import com.example.Bank.storage.SharedPrefManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {

    private  var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root

        emailFocusListener()

        binding.btnSaveRegister.setOnClickListener {
            userSignUp()

        }
        binding.btnLogRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view

    }

    private fun userSignUp() {
        val name = binding.etNameRegister.text.toString().trim()
        val email = binding.etEmailRegister.text.toString().trim()
        val password = binding.etPasswordRegister.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        if (email.isEmpty()) {
            binding.etEmailRegister.error = "Email is required"
            binding.etEmailRegister.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailRegister.error = "Enter a valid email"
            binding.etEmailRegister.requestFocus()
            return
        }
        if (password.isEmpty()) {
            binding.etPasswordRegister.error = "Password required"
            binding.etPasswordRegister.requestFocus()
            return
        }
        if (password.length < 6) {
            binding.etPasswordRegister.error = "Password should be atleast 6 character long"
            binding.etPasswordRegister.requestFocus()
            return
        }
        if (name.isEmpty()) {
            binding.etNameRegister.error = "Name required"
            binding.etNameRegister.requestFocus()
            return
        }
        if (phone.isEmpty()) {
            binding.etPhone.error = "School required"
            binding.etPhone.requestFocus()
            return
        }
        val call = RetrofitClient
            .instance
            ?.api
            ?.createUser(email, password, name, phone)
        call?.enqueue(object : Callback<LoginRespons?> {
            override fun onResponse(
                call: Call<LoginRespons?>,
                response: Response<LoginRespons?>
            ) {

                if (response.code() == 200) {
                    val dr = response.body()
                    SharedPrefManager.getInstance(requireContext())
                        ?.saveUser(dr!!.data)
                    MaterialAlertDialogBuilder(requireContext()).setTitle("خد بالك يا جدع")
                        .setMessage(dr?.message).setPositiveButton("OK") {_,_ ->
                        }.show()



                }
            }

            override fun onFailure(call: Call<LoginRespons?>, t: Throwable) {
                MaterialAlertDialogBuilder(requireContext()).setTitle("خد بالك يا جدع")
                    .setMessage(t.message).setPositiveButton("OK") {_,_ ->
                    }.show()


            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
    private fun emailFocusListener()
    {
        binding.etEmailRegister.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = binding.etEmailRegister.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }
}
