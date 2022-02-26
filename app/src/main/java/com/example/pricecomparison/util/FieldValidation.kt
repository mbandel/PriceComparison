package com.example.pricecomparison.util

import android.util.Patterns

object FieldValidation {
    fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}\$"
        return password.matches(Regex(passwordPattern))
    }
}
