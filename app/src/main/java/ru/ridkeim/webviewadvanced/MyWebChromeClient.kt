package ru.ridkeim.webviewadvanced

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.ActionBar

class MyWebChromeClient(private val parentActivity : Activity, private val bar: ActionBar?) : WebChromeClient() {
    companion object{
        const val REQUEST_CODE = 42
    }
    private val callBackMap = mutableMapOf<Int,ValueCallback<Array<Uri>>?>()
    override fun onReceivedTitle(view: WebView?, title: String?) {
        bar?.title = title
        super.onReceivedTitle(view, title)
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        bar?.let {
            val bitmapDrawable = BitmapDrawable(parentActivity.resources, icon)
            bar.setIcon(bitmapDrawable)
        }
        super.onReceivedIcon(view, icon)
    }

    override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?): Boolean {
        val intent = fileChooserParams?.createIntent()
        parentActivity.startActivityForResult(intent, REQUEST_CODE)
        callBackMap[REQUEST_CODE] = filePathCallback
        return true
    }

    fun retrieveCallBackForCode(code : Int) : ValueCallback<Array<Uri>>?{
        return callBackMap.remove(code)
    }
}