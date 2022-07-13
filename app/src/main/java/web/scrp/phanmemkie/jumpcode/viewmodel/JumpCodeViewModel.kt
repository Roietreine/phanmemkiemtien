package web.scrp.phanmemkie.jumpcode.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import web.scrp.phanmemkie.jumpcode.network.data.Request
import web.scrp.phanmemkie.jumpcode.network.data.Response
import web.scrp.phanmemkie.jumpcode.network.repository.JumpServiceImpl
import web.scrp.phanmemkie.jumpcode.utils.UiState

class JumpCodeViewModel : ViewModel() {

    private val repo = JumpServiceImpl()

    private val _urlResponse = MutableLiveData<UiState<Response>>()
    val urlResponse : LiveData<UiState<Response>>
        get() = _urlResponse

    fun getJumpUrl(packageName: String){
        val param = Request(
            packageName
        )
        viewModelScope.launch {
            repo.getJumpCodeUrl(param)
                .catch { err -> _urlResponse.value = UiState.Error(err) }
                .collectLatest {
                    _urlResponse.value = UiState.Success(it)
                }
        }
    }
}