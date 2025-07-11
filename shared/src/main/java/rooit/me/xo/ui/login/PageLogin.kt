package rooit.me.xo.ui.login

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rooit.me.xo.R
import rooit.me.xo.theme.SimpleNews
import androidx.compose.ui.res.painterResource
import rooit.me.xo.utils.toJsonObject
import rooit.me.xo.utils.toMap
import timber.log.Timber

@Composable
fun PageLogin(
    modifier: Modifier = Modifier,
    onNavigateToNews: (result: String?) -> Unit,
    args: String? = null
) {
    val context = LocalContext.current
    val drawable = remember {
        AppCompatResources.getDrawable(context, R.drawable.compose_multiplatform)
    }
    LaunchedEffect(args) {
        Timber.e("Show me   PageSplash received args: $args")
    }
    val processed = mutableMapOf<String, Any>()
    args?.let {
        processed.putAll(it.toMap())
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            processed.put("Login", "News")
            onNavigateToNews(processed.toJsonObject().toString())
        }) {
            Text("Click to News")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.compose_multiplatform),
            contentDescription = "Compose Multiplatform"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleNews {
        PageLogin(
            onNavigateToNews = {}
        )
    }
}