package ru.ridkeim.webviewadvanced

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ridkeim.webviewadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var myWebChromeClient: MyWebChromeClient
    private lateinit var vBinding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)
        myWebChromeClient = MyWebChromeClient(this@MainActivity, this@MainActivity.supportActionBar)
        vBinding.webView.apply {
            settings.apply {
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = true
                displayZoomControls = false
                allowFileAccess = false
                allowFileAccessFromFileURLs = false
                allowUniversalAccessFromFileURLs = false
                allowContentAccess = true
                javaScriptEnabled = true
                addJavascriptInterface(MyJavaScriptInterface(this@MainActivity),"test")

            }
            webViewClient = MyWebClient(this@MainActivity)
            webChromeClient = myWebChromeClient
            loadUrl("https://appassets.ridkeim.ru/assets/login.html")
        }
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val filePathCallback = myWebChromeClient.retrieveCallBackForCode(requestCode)
        if(resultCode == RESULT_OK){
            data?.data?.let{
                filePathCallback?.onReceiveValue(arrayOf(it))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}