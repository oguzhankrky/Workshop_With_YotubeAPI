package com.example.workshopwithyotubeapi


import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText



fun View.hideKeyboard() { // extra added when push button , keyboard is closed.
        val inputMethodManager = this.context.applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}



