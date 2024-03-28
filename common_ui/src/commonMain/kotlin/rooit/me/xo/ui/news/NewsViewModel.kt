package rooit.me.xo.ui.news

//import android.util.Log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import rooit.me.xo.repository.AuthRepository
import rooit.me.xo.repository.NewsRepository

//public class NewsViewModel(public val repo: NewsRepository ) : ViewModel() {
public class NewsViewModel(public val repo: AuthRepository) : ViewModel() {
    //Ref : https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=zh-tw
    //      https://medium.com/@chn_dr/fetching-api-using-retrofit-a44906a8499d
    private val _uiState = MutableStateFlow(rooit.me.xo.model.NewAction.NewState())
    public val uiState: MutableStateFlow<rooit.me.xo.model.NewAction.NewState> = _uiState

    public fun dispatch(action: rooit.me.xo.model.NewAction) {
        when (action) {
            is rooit.me.xo.model.NewAction.Load -> fetchTopHeadlines()
        }
    }

    private fun fetchTopHeadlines() {
//        repo.fetchTopHeadlinesFromDBFlow()
//            .onEach {
//                 _uiState.setState {
//                    copy(it)
//                }
//            }
//            .launchIn(viewModelScope)
//        repo.fetchTopHeadlinesFlow("us", "f53a2c1085bd4e19859f4f3b07f2babf")
//            .onStart {
//                _uiState.setState {
//                    copy(emptyList())
//                }
//            }
//            .onEach { articles ->
//                repo.syncResultIntoDB(articles)
//            }
//            .catch {
////                Timber.e(it)
//            }
//            .launchIn(viewModelScope)
        repo.fetchCodeForMobile()
            .onStart {
//                Log.e("weqed","Show SSS me $this")
            }.onEach {
//                 Log.e("weqed","Show me $it")
            }.catch {
//                Log.e("weqed","Show !! $this")
            }.launchIn(viewModelScope)
    }
}

private fun <T> MutableStateFlow<T>.setState(reducer: T.() -> T) {
    this.value = this.value.reducer()
}