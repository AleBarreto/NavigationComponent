package com.example.navigationcomponent.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationcomponent.R

class LoginViewModel : ViewModel() {

    sealed class AuthenticationState {
        class InvalidAuthentication(val fields: List<Pair<String, Int>>) : AuthenticationState()
    }

    val authenticationStateEvent = MutableLiveData<AuthenticationState>()


    fun authentication(userName: String, userPassword: String) {
        if (isValidForm(userName,userPassword)) {
            //user autenticado
        }
    }

    private fun isValidForm(userName: String, userPassword: String) : Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()

        if (userName.isEmpty()) {
            invalidFields.add(INPUT_USERNAME)
        }

        if (userPassword.isEmpty()) {
            invalidFields.add(INPUT_PASSWORD)
        }

        if (invalidFields.isNotEmpty()){
            authenticationStateEvent.value = AuthenticationState.InvalidAuthentication(invalidFields)
            return false
        }

        return true

    }

    companion object {
        val INPUT_USERNAME = "INPUT_USERNAME" to R.string.login_input_layout_error_invalid_username
        val INPUT_PASSWORD = "INPUT_PASSWORD" to R.string.login_input_layout_error_invalid_password
    }


}