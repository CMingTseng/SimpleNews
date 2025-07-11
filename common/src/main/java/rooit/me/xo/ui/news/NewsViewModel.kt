package rooit.me.xo.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import rooit.me.xo.repository.NewsRepository
import timber.log.Timber

class NewsViewModel(private val repo: NewsRepository, private val apikey: String) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init {
        // Observe DB changes as the primary source of truth for the article list
        observeDatabaseChanges()
        // Trigger initial data load
        dispatch(NewsViewAction.InitialLoadOrRetry)
    }

    private fun observeDatabaseChanges() {
        viewModelScope.launch {
            repo.observeArticlesFromDB()
                .catch { e ->
                    Timber.e(e, "Error observing DB changes")
                    _uiState.update { it.copy(userMessage = "Database error: ${e.localizedMessage}") }
                }
                .collectLatest { articlesFromDb ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            articles = articlesFromDb,
                            // isLoading might be true due to network fetch, don't override it here
                            // unless DB emitting means loading is done.
                            // isRefreshing is handled by network calls.
                            userMessage = if (articlesFromDb.isEmpty() && !currentState.isLoading && !currentState.isRefreshing) "No news available." else null
                        )
                    }
                }
        }
    }

    fun dispatch(action: NewsViewAction) {
        when (action) {
            NewsViewAction.InitialLoadOrRetry -> loadNews(isRefresh = false)
            NewsViewAction.RefreshNews -> loadNews(isRefresh = true)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadNews(isRefresh: Boolean) {
        // Prevent multiple simultaneous loads unless it's a forced refresh
        if ((_uiState.value.isLoading || _uiState.value.isRefreshing) && !isRefresh && !repo.isCurrentlyFetchingFromNetwork()) {
            Timber.d("Load/Refresh in progress, skipping.")
            return
        }

        viewModelScope.launch {
            _uiState.update {
                if (isRefresh) it.copy(isRefreshing = true, userMessage = null)
                else it.copy(isLoading = true, userMessage = null)
            }

            try {
                Timber.d("Attempting to fetch articles from network. Is refresh: $isRefresh")
                val articlesFromNetwork = repo.fetchTopHeadlinesFromNetwork("us", apikey)
                if (articlesFromNetwork.isNotEmpty()) {
                    repo.syncArticlesToDB(articlesFromNetwork)
                    Timber.d("Successfully fetched ${articlesFromNetwork.size} articles and synced to DB.")
                    // DB observation will update the list
                } else {
                    Timber.d("Network fetch returned empty list or was skipped.")
                    // If DB is also empty, it will be reflected by the DB observer
                    if (_uiState.value.articles.isEmpty()){
                        _uiState.update { it.copy(userMessage = "No new articles found from network.") }
                    }
                }
            } catch (e: IOException) {
                Timber.e(e, "Network error during loadNews")
                _uiState.update { it.copy(userMessage = "Network error. Please check your connection.") }
            } catch (e: Exception) {
                Timber.e(e, "Generic error during loadNews")
                _uiState.update { it.copy(userMessage = "An unexpected error occurred.") }
            } finally {
                _uiState.update {
                    if (isRefresh) it.copy(isRefreshing = false)
                    else it.copy(isLoading = false)
                }
                Timber.d("loadNews finished. Is refresh: $isRefresh")
            }
        }
    }
}