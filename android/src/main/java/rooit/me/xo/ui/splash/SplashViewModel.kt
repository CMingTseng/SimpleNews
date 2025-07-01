package rooit.me.xo.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Splash , Click me to Next "
    }
    val text: LiveData<String> = _text
}