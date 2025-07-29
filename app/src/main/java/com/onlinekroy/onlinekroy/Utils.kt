package com.onlinekroy.onlinekroy

import android.widget.EditText

fun EditText.isEmpty(): Boolean{
    return  if(this.text.toString().isEmpty()){
        this.error = "This Input Must Be Fill Up !"
        true
    }else{
        false
    }
}