package rooit.me.xo.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import rooit.me.xo.BuildConfig
import rooit.me.xo.model.Article
import rooit.me.xo.repository.NewsRepository
import timber.log.Timber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class NewsViewModel (private val repo: NewsRepository) : ViewModel() {

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _allNews : MutableStateFlow<List<Article>> = MutableStateFlow(listOf())
    val allNews : StateFlow<List<Article>> = _allNews

    fun fetchTopHeadlines() {
        repo.fetchTopHeadlinesFromDBFlow()
            .onEach {
                _allNews.value=it
            }
            .launchIn(viewModelScope)
        repo.fetchTopHeadlinesFlow("us", BuildConfig.API_KEY)
            .onStart { _isRefreshing.value = true }
            .onEach { articles ->
                repo.syncResultIntoDB(articles)
                _isRefreshing.value = false
            }
            .catch {
                _isRefreshing.value = false
                Timber.e(it)
            }
            .launchIn(viewModelScope)
    }
}