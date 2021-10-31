package com.example.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int,val answer:Boolean) {//model for this project

    var name = ""
    constructor(textResId:Int, answer:Boolean,name:String):this(textResId = textResId,answer = answer){
        this.name = name
    }

}
