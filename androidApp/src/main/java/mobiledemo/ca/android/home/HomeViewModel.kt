package mobiledemo.ca.android.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mobiledemo.ca.ProductsApi
import mobiledemo.ca.RequestState

class HomeViewModel : ViewModel() {
    private var _requestState: MutableState<RequestState> = mutableStateOf(RequestState.Idle)
    val requestState: State<RequestState> = _requestState

    init {
        viewModelScope.launch(Dispatchers.Main) {
            ProductsApi().fetchProducts(limit = 10).collectLatest {
                _requestState.value = it
            }
        }
    }
}
