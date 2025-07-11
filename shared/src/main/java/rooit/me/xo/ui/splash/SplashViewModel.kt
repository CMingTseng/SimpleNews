package rooit.me.xo.ui.splash

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class SplashViewModel : ViewModel() {
    private val _text = MutableStateFlow("This is Splash , Click me to Next ") // 1. 直接在構造函數中提供初始值
    val text: LiveData<String> = _text.asLiveData()
//    val text: StateFlow<String> = _text.asStateFlow()
}