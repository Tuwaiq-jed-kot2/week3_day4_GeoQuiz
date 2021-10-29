package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var solved: Boolean, var instructor: String, var isCheater: Boolean, var hint: String) { //doesn't compile until the resource is found - make sure the string isn't hardcoded - prefer to use this name for string to remember

}