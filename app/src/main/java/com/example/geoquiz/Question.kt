package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResIdRes: Int, val answer:Boolean)
