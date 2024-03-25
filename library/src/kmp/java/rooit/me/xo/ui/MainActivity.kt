package rooit.me.xo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.koin.androidx.viewmodel.ext.android.viewModel
import rooit.me.xo.theme.SimpleNewsTheme
import rooit.me.xo.ui.news.NewsScreen
import rooit.me.xo.ui.news.NewsViewModel

class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewsScreen(viewModel = vm)
                }
            }
        }
    }
}