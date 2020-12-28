package ru.ridkeim.webviewadvanced

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MyWebClient : WebViewClient() {
    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        Toast.makeText(view.context,"url=$url scale=${view.scale}",Toast.LENGTH_SHORT).show()
    }
}