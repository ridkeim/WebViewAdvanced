package ru.ridkeim.webviewadvanced

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.ActionBar

class MyWebChromeClient(private val context : Context, private val bar: ActionBar?) : WebChromeClient() {
    override fun onReceivedTitle(view: WebView?, title: String?) {
        bar?.title = title
        super.onReceivedTitle(view, title)
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        bar?.let {
            val bitmapDrawable = BitmapDrawable(context.resources, icon)
            bar.setIcon(bitmapDrawable)
        }
        super.onReceivedIcon(view, icon)
    }
}