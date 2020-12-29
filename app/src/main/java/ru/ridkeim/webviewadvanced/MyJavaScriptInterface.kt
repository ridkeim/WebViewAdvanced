package ru.ridkeim.webviewadvanced

class MyJavaScriptInterface {
    @android.webkit.JavascriptInterface
    fun getGreeting() : String {
        return "Hello from JavaScript!"
    }
}