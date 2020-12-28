package ru.ridkeim.webviewadvanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ridkeim.webviewadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityMainBinding
    companion object{
        private const val IMG_GALLERY_REQUEST_CODE = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)
        vBinding.buttonGallery.setOnClickListener {
            Intent().apply {
                action = Intent.ACTION_OPEN_DOCUMENT
                type = "image/*"
            }.also {
                startActivityForResult(Intent.createChooser(it,getString(R.string.choose_pic)),
                    IMG_GALLERY_REQUEST_CODE)
            }
        }
        vBinding.buttonLocal.setOnClickListener {
            vBinding.webView.loadUrl(
                "https://appassets.ridkeim.ru/assets/asset_cat.png"
//                "https://appassets.ridkeim.ru/res/drawable/cat.png"
//                "file:///android_asset/asset_cat.png"
            )
        }
        vBinding.buttonWeb.setOnClickListener {
            vBinding.webView.loadUrl("http://developer.alexanderklimov.ru/android/images/updowncat.jpg")
        }
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
            }
            webViewClient = MyWebClient(this@MainActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                IMG_GALLERY_REQUEST_CODE -> {
                    if (data != null && data.data != null) {
                        val uri = data.data!!
                        vBinding.webView.loadUrl(uri.toString())
                    }
                }
                else -> {}
            }
        }
    }
}