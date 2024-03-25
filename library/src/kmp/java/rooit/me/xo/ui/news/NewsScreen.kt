package rooit.me.xo.ui.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import rooit.me.xo.theme.SimpleNewsTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import com.seiko.imageloader.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//    fun NewsScreen (onNavigate: (Todo?) -> Unit) {
fun NewsScreen (viewModel: NewsViewModel) {
//    val viewModel = viewModel(NewsViewModel::class.java)
    val allnews = viewModel.allNews.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "News",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                },
            )
        },
    ) { innerPadding ->
       LazyColumn(Modifier.fillMaxSize()) {
            items(items = allnews.value ){
                NewsItem(it.urlToImage ?: "", it.title ?: "none")
            }
        }
        LaunchedEffect(Unit) {
            viewModel.fetchTopHeadlines()
        }
    }
}

@Composable
public fun NewsItem(imgUrl: String, title: String) {
    val imagePainter = rememberImagePainter(imgUrl)
    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = imagePainter,
                contentDescription = "head",
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                text = title,
                fontSize = 18.sp,
                maxLines = 2
            )
        }
        SpaceLine()
    }
}

@Composable
public fun SpaceLine() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xffdddddd))
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleNewsTheme {
//        val previewViewModel = NewsViewModel()
//        NewsScreen(viewModel = previewViewModel)
    }
}