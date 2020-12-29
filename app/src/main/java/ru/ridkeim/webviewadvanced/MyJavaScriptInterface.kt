package ru.ridkeim.webviewadvanced

import android.content.Context
import android.widget.Toast

class MyJavaScriptInterface(private val context: Context) {
    @android.webkit.JavascriptInterface
    fun getGreeting() : String {
        return "Hello from Kotlin!"
    }

    @android.webkit.JavascriptInterface
    fun showToast(toast : String) {
        Toast.makeText(context,toast,Toast.LENGTH_SHORT).show()
    }
}