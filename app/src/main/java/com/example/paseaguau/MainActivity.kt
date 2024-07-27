package com.example.paseaguau

import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.tooling.preview.Preview
import com.example.paseaguau.ui.theme.PaseaguauTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaseaguauTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    WebViewScreen()
                }
            }
        }
    }
}

@Composable
fun WebViewScreen(modifier: Modifier = Modifier) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: android.webkit.WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    android.util.Log.e("WebViewError", "Error loading webpage: ${error?.description}")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    android.util.Log.d("WebView", "Finished loading: $url")
                }
            }
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            loadUrl("https://paseaguau.com")
        }
    }, modifier = modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PaseaguauTheme {
        WebViewScreen()
    }
}
