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
                startActivityForResult(Intent.createChooser(it,"Выберите картиинку"),
                    IMG_GALLERY_REQUEST_CODE)
            }
        }
        vBinding.buttonLocal.setOnClickListener {
            vBinding.webView.loadUrl(
                "file:///android_res/drawable/cat.png")
//            val resId = R.drawable.cat
//            val uri = Uri.parse(
//                "${ContentResolver.SCHEME_ANDROID_RESOURCE
//                }://${resources.getResourcePackageName(resId)
//                }/${resources.getResourceTypeName(resId)
//                }/${resources.getResourceEntryName(resId)}"
//            )
//            vBinding.webView.loadUrl(
//                uri.toString())
        }
        vBinding.buttonWeb.setOnClickListener {
            vBinding.webView.loadUrl("http://developer.alexanderklimov.ru/android/images/updowncat.jpg");
        }
        vBinding.webView.apply {
            settings.useWideViewPort = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            webViewClient = MyWebClient()
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