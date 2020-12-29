package ru.ridkeim.webviewadvanced

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.Toast
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat
import java.io.File
import java.net.URLConnection

class MyWebClient(context : Context) : WebViewClientCompat() {
    private val assetLoader: WebViewAssetLoader = WebViewAssetLoader.Builder()
        .setDomain("appassets.ridkeim.ru")
        .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(context))
        .addPathHandler("/public/", WebViewAssetLoader.InternalStoragePathHandler(
            context,
            File(context.filesDir, "public")
        ))
        .addPathHandler("/res/",WebViewAssetLoader.ResourcesPathHandler(context))
        .build()

    @Suppress("DEPRECATION")
    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        Toast.makeText(view.context,"url=$url\nscale=${view.scale}",Toast.LENGTH_SHORT).show()
        val user = "user3421"
        val password = " asLkl_*761rsdfa"
        view.loadUrl("javascript:(function(){document.getElementById('username').value = '${
            user
        }'; document.getElementById('password').value='${
            password
        }';})()")
    }

    override fun shouldInterceptRequest(
        view: WebView,
        request: WebResourceRequest
    ): WebResourceResponse? {
        return assetLoader.shouldInterceptRequest(request.url)
    }
}