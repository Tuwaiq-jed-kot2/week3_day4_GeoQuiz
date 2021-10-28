package com.example.geoquiz

import androidx.annotation.StringRes

class Questions(@StringRes var stringResId : Int, val answer : Boolean, var isAnswered : Boolean,val writtenBy : String)
