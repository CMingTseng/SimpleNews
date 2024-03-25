package rooit.me.xo.ui.flow.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainFlowViewModel  ( ) : ViewModel() {


    fun fetchDialog(cover: String) {
        viewModelScope.launch {

        }
    }

    fun fetchSelfInfo() {
        viewModelScope.launch {

        }
    }
}