package rooit.me.xo.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import rooit.me.xo.BuildConfig
import timber.log.Timber
import kotlinx.coroutines.flow.MutableStateFlow
import rooit.me.xo.model.NewAction
import rooit.me.xo.repository.NewsRepository

public class NewsViewModel(private val repo: NewsRepository) : ViewModel() {
    //Ref : https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=zh-tw
    //      https://medium.com/@chn_dr/fetching-api-using-retrofit-a44906a8499d
    private val _uiState = MutableStateFlow(NewAction.NewState())
    public val uiState: MutableStateFlow<NewAction.NewState> = _uiState

    public fun dispatch(action: NewAction) {
        when (action) {
            is NewAction.Load -> fetchTopHeadlines()
        }
    }

    private fun fetchTopHeadlines() {
        repo.fetchTopHeadlinesFromDBFlow()
            .onEach {

                _uiState.setState {
                    copy(it)
                }
            }
            .launchIn(viewModelScope)
        repo.fetchTopHeadlinesFlow("us", BuildConfig.API_KEY)
            .onStart {
                _uiState.setState {
                    copy(emptyList())
                }
            }
            .onEach { articles ->
                repo.syncResultIntoDB(articles)
            }
            .catch {
                Timber.e(it)
            }
            .launchIn(viewModelScope)
    }
}

private fun <T> MutableStateFlow<T>.setState(reducer: T.() -> T) {
    this.value = this.value.reducer()
}