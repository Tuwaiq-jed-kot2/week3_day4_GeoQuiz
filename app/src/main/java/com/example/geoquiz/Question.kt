package com.example.newgeoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val questionResId:Int, val answer:Boolean)