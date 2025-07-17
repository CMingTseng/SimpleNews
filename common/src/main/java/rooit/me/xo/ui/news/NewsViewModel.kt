package rooit.me.xo.ui.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rooit.me.xo.repository.NewsRepository

class NewsViewModel(private val repo: NewsRepository, private val apikey: String) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    // 用於持有正在收集文章資料流的 Job，以便可以取消和重啟它
    private var articlesJob: Job? = null

    init {
        // 觸發初始資料載入
        dispatch(NewsViewAction.InitialLoadOrRetry)
    }

    /**
     * 處理來自 UI 的所有操作。
     */
    fun dispatch(action: NewsViewAction) {
        when (action) {
            NewsViewAction.InitialLoadOrRetry -> loadNews(isRefresh = false)
            NewsViewAction.RefreshNews -> loadNews(isRefresh = true)
        }
    }

    /**
     * 核心函式，負責訂閱和更新新聞資料。
     * 每次呼叫時，它會取消先前的訂閱並建立一個新的，從而有效地觸發刷新。
     *
     * @param isRefresh 指示這次載入是否由下拉刷新觸發。
     */
    private fun loadNews(isRefresh: Boolean) {
        // 1. 取消上一個正在執行的 Job，以防重複載入
//        articlesJob?.cancel()
        Log.e("NewsViewModel","Show me loadNews ")
        // 2. 啟動一個新的協程來收集資料流
        articlesJob = viewModelScope.launch {
            // 3. 立即更新 UI 狀態以顯示載入指示器
            _uiState.update {
                it.copy(
                    isLoading = !isRefresh,
                    isRefreshing = isRefresh,
                    userMessage = null
                )
            }
            Log.e("NewsViewModel","Show me getArticles ")
            // 4. 呼叫 Repository 的單一入口函式
            repo.getArticles("us", apikey)
                .catch { e ->
                    // 如果 Flow 本身發生無法恢復的錯誤（例如資料庫損壞），則捕獲它
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            userMessage = "An unexpected error occurred: ${e.message}"
                        )
                    }
                }
                .collect { articlesFromRepo ->
                    // 5. 每次收到來自 Repository 的新資料列表時，更新 UI
                    //    同時關閉載入/刷新指示器，因為資料已經呈現
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            articles = articlesFromRepo,
                            userMessage = if (articlesFromRepo.isEmpty() && !it.isLoading) "No news available." else null
                        )
                    }
                }
        }
    }
}