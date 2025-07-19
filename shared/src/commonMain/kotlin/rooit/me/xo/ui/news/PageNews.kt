package rooit.me.xo.ui.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items // 导入 items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.TopAppBarDefaults
import rooit.me.xo.theme.SimpleNews
import coil3.compose.AsyncImage
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.unit.sp
import rooit.me.xo.utils.log.Log

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PageNews(modifier: Modifier = Modifier, vm: NewsViewModel = koinViewModel(), onNewsItemClickedAndPopBack: (result: String?) -> Unit, args:String?=null)  {
    val state by vm.uiState.collectAsState()
    val context = LocalContext.current

    // State for Pull-to-Refresh
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { vm.dispatch(NewsViewAction.RefreshNews) }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
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
        // LaunchedEffect for showing user messages as Toasts (can be improved with Snackbar)
        LaunchedEffect(state.userMessage) {
            state.userMessage?.let { message ->
                // Avoid showing "Loading..." or "No news" as toast constantly
                if (message != "Loading news..." && message != "No news available." && message != "No new articles found from network.") {
                    android.widget.Toast.makeText(
                        context,
                        message,
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                    // Consider clearing the message in ViewModel after showing to prevent re-show on config change
                    // vm.clearUserMessage() // You'd need to add this method to ViewModel
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullRefresh(pullRefreshState) // Apply PullRefresh modifier to the Box containing the list
        ) {
            if (state.isLoading && state.articles.isEmpty() && !state.isRefreshing) {
                // Initial Loading State (when list is empty)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (state.articles.isEmpty() && !state.isLoading && !state.isRefreshing) {
                // Empty State (no articles, not loading)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = state.userMessage ?: "No news available at the moment.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                // Content State (displaying articles)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(
                        items = state.articles,
                        // 使用更可靠的唯一ID，如果 Article 有的话。例如 article.url 或 article.id
                        key = { article -> article.url ?: article.title.hashCode() }
                    ) { article ->
                        NewsItem(
                            imgUrl = article.urlToImage ?: "",
                            title = article.title ?: "No Title",
                            content = article.content ?: article.description ?: "",
                            onItemClick = {
                                Log.i("PageNews","News item clicked: ${article.title}")

                                onNewsItemClickedAndPopBack("Show me back form news")
                                // navController.navigate("newsDetail/${article.url}")
                            }
                        )
                    }
                }
            }

            // PullRefreshIndicator should be on top of the content
            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                backgroundColor = MaterialTheme.colorScheme.surface, // Optional: customize indicator background
                contentColor = MaterialTheme.colorScheme.primary     // Optional: customize indicator color
            )
        }
    }
}

@Composable
fun NewsItem(imgUrl: String, title: String, content: String, onItemClick: () -> Unit ) { // Added content parameter
//    Column(Modifier.padding(horizontal = 16.dp)) {
//        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
//            AsyncImage(
//                model = imgUrl,
//                contentDescription = "News article image for $title",
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(4.dp)),
//                contentScale = ContentScale.Crop,
//            )
//            Text(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 8.dp),
//                text = title,
//                fontSize = 18.sp,
//                maxLines = 2
//            )
//        }
//        SpaceLine()
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable(onClick = onItemClick), // <<< 在 Card 上应用 clickable 修饰符
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) { // 给 Column 也加点垂直 padding
            Row(
                // Modifier.padding(vertical = 8.dp) // 从 Row 移到 Column
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (imgUrl.isNotBlank()) {
                    AsyncImage(
                        model = imgUrl,
                        contentDescription = "News article image for $title",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
//                    modifier = Modifier.weight(1f) // 让文本占据剩余空间
                    text = title,
                    fontSize = 18.sp,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun SpaceLine() { // This can be replaced by Divider()
    Divider( // Using Material's Divider
        color = MaterialTheme.colorScheme.outlineVariant, // Use theme color
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp) // Match item padding if needed outside Card
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleNews {
        PageNews(onNewsItemClickedAndPopBack={})
    }
}