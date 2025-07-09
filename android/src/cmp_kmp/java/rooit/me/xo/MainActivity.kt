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
