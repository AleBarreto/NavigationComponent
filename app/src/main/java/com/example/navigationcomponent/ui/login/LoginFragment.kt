package com.example.navigationcomponent.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.navigationcomponent.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.authenticationStateEvent.observe(
            viewLifecycleOwner, Observer { authenticationState ->
                when (authenticationState) {
                    is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                        val validateFields: Map<String, TextInputLayout> = initValidationFields()
                        authenticationState.fields.forEach { fieldError ->
                            validateFields[fieldError.first]?.error = getString(fieldError.second)
                        }
                    }
                }
            })

        buttonLoginSignIn.setOnClickListener {
            val userName = inputLoginUsername.text.toString()
            val userPassword = inputLoginPassword.text.toString()
            viewModel.authentication(userName, userPassword)
        }

    }

    private fun initValidationFields() = mapOf(
        LoginViewModel.INPUT_USERNAME.first to inputLayoutLoginUsername,
        LoginViewModel.INPUT_PASSWORD.first to inputLayoutLoginPassword
    )

}