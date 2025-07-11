package rooit.me.xo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import rooit.me.xo.navigation.KDslNavigationBuilder
import rooit.me.xo.route.Route
import rooit.me.xo.utils.toUriJsonString


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var args_string=""
        intent?.extras?.let { bundle ->
            args_string = bundle.toUriJsonString()
        }
        //FIXME for test Uri.encode() and Uri.decode()
//        val demo = "splash/{\"name\":\"John Doe\",\"city\":\"New York\"}"
//        Timber.e("Show me  demo string  \n $demo")
//        val androidurie= Uri.encode(demo)
//        val androiduri= Uri.decode(androidurie)
//        Timber.e("Show me  android  uri  encode   string  \n $androidurie  \n decode \n $androiduri")
//        val urlencodere= UrlEncoderUtil.encode(demo)
//        val urlencoder= UrlEncoderUtil.decode(urlencodere)
//        val urlencoderandroiduri= Uri.decode(urlencodere)
//        val androiduriurlencoder= UrlEncoderUtil.decode(androidurie)
//        Timber.e("Show me  urlencoder   string  \n $urlencodere  \n decode \n $urlencoder")
//        Timber.e("Show me   assert   \n $urlencoderandroiduri  \n    $androiduriurlencoder  \n  assert  ${urlencoderandroiduri==androiduriurlencoder}")
        setContent {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "${Route.Splash.routeName}/${args_string}",
                    modifier = Modifier.padding(innerPadding),
                    builder = KDslNavigationBuilder().defineGraphContent(
                        navController = navController,
                        startDest = Route.Splash,
                        args_string=args_string
                    )
                )
            }
        }
    }
}
