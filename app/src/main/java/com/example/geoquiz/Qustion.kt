package com.example.geoquiz

import androidx.annotation.StringRes

data class Qusetion(@StringRes val textResId:Int, val answer:Boolean, var isSolved:Boolean)

