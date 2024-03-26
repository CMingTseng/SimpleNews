package rooit.me.xo.ui

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.PreComposeApp
import org.koin.compose.KoinContext
import rooit.me.xo.ui.news.NewsScreen

@Composable
public fun UIShow() {
    PreComposeApp {
        KoinContext {
            Surface(Modifier.systemBarsPadding()) {
                NewsScreen()
            }
        }
    }
}