package rooit.me.xo.ui.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rooit.me.xo.theme.SimpleNews
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.dp
import rooit.me.xo.Res
import rooit.me.xo.compose_multiplatform
import rooit.me.xo.utils.log.Log
import rooit.me.xo.utils.toJsonObject
import rooit.me.xo.utils.toMap
@Composable
fun PageSplash( modifier: Modifier = Modifier, onNavigateToLogin: (result: String?) -> Unit, onNavigateToNews: (result: String?) -> Unit ,args:String?=null) {

    LaunchedEffect(args) {
        Log.i("PageSplash","Show me   PageSplash received args: $args")
    }
    val processed = mutableMapOf<String, Any>()
    args?.let {
        processed.putAll(it.toMap() )
    }
    val painter =  painterResource(Res.drawable.compose_multiplatform)
    Column(
        modifier = modifier.fillMaxWidth(), // 让 Column 占据整个宽度，以便 Row 内的分布生效
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // 可选：如果希望整个内容垂直居中
    ) {
        Image(
            painter = painter,
            contentDescription = "Compose Multiplatform Logo",
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Get Args : ${args ?: ""}",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    processed.put("Splash" , "Login")
                    onNavigateToLogin(  processed.toJsonObject().toString())
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Click to Login")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    processed.put("Splash" , "New")
                    onNavigateToNews(  processed.toJsonObject().toString())
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Click to News")
            }
        }

         Spacer(modifier = Modifier.height(16.dp))
    }
}


@Preview(showBackground = true, name = "Splash Page Preview")
@Composable
fun GreetingPreview() {
    SimpleNews {
        PageSplash(
            onNavigateToLogin = {},
            onNavigateToNews = {}
        )
    }
}