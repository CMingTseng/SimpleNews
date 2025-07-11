package rooit.me.xo.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _text = MutableStateFlow("Demo Login Page click to finish")
    val text: StateFlow<String> = _text.asStateFlow()

}