package ru.ridkeim.webviewadvanced

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ridkeim.webviewadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)
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
                addJavascriptInterface(MyJavaScriptInterface(),"test")

            }
            webViewClient = MyWebClient(this@MainActivity)
            webChromeClient = MyWebChromeClient(this@MainActivity.baseContext,this@MainActivity.supportActionBar)
            loadUrl("https://appassets.ridkeim.ru/assets/login.html")
        }
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }
}