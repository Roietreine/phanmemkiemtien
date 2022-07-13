package web.scrp.phanmemkie.privacy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import web.scrp.phanmemkie.common.model.DataModel
import web.scrp.phanmemkie.common.utils.Utilities.Companion.privacyDesc
import web.scrp.phanmemkie.common.utils.Utilities.Companion.privacyTitle

class PrivacyViewModel : ViewModel(){

    private var privacyList = ArrayList<DataModel>()
    private var privacyInfo = MutableLiveData<List<DataModel>>()
    val trmNf : LiveData<List<DataModel>>
        get() = privacyInfo
    private var privacyError = CoroutineExceptionHandler { _, _ ->
        privacyInfo.postValue(null)
    }

    fun termiFun(): MutableLiveData<List<DataModel>> {
        viewModelScope.launch(privacyError + Dispatchers.IO) {
            for (n in privacyTitle.indices) {
                privacyList.add(DataModel(privacyTitle[n], privacyDesc[n]))
            }
            privacyInfo.postValue(privacyList)
        }
        return privacyInfo
    }
}