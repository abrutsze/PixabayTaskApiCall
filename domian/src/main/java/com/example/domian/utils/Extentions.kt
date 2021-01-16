package com.example.domian.utils

import android.util.Patterns

fun String.isUserNameValid(): Boolean {
    return if (this.contains("@")) {
        Patterns.EMAIL_ADDRESS.matcher(this).matches()
    } else {
        this.isNotBlank()
    }
}
