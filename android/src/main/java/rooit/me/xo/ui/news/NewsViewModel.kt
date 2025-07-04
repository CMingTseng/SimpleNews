package rooit.me.xo.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import rooit.me.xo.BuildConfig
import rooit.me.xo.model.Article
import rooit.me.xo.repository.NewsRepository
import timber.log.Timber
class NewsViewModel (private val repo: NewsRepository) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()
    private val _allNews = MutableStateFlow<List<Article>>(emptyList())
    val allNews: StateFlow<List<Article>> = _allNews.asStateFlow()

    init {
        loadInitialNewsFromDB()
    }
    private fun loadInitialNewsFromDB() {
        repo.fetchTopHeadlinesFromDBFlow()
            .onEach { articlesFromDB ->
                _allNews.value = articlesFromDB
            }
            .catch { e ->
                Timber.e(e, "Error loading news from DB")
            }
            .launchIn(viewModelScope)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchTopHeadlines(forceRefresh: Boolean = true) {
        if (_isRefreshing.value && !forceRefresh) {
            Timber.d("Already refreshing, skipping network request.")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchTopHeadlinesFlow("us", BuildConfig.API_KEY)
                .onStart {
                    _isRefreshing.value = true
                    Timber.d("Network refresh started.")
                }
                .mapLatest { articlesFromNetwork ->
                    try {
                        repo.syncResultIntoDB(articlesFromNetwork)
                        Timber.d("Network articles synced to DB.")
                    } catch (e: Exception) {
                        Timber.e(e, "Error syncing network articles to DB")
                    }
                    articlesFromNetwork // 或者返回 Unit
                }
                .onCompletion {
                    _isRefreshing.value = false
                    Timber.d("Network refresh completed (onCompletion).")
                }
                .catch { e ->
                    Timber.e(e, "Error fetching top headlines from network")
                }
                .collect()
        }
    }
}