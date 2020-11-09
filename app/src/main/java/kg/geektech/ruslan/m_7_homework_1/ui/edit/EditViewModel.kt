package kg.geektech.ruslan.m_7_homework_1.ui.edit

import android.webkit.URLUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    val showToast = MutableLiveData<String>()
    val result = MutableLiveData<String>()
    val isFinished = MutableLiveData<Boolean>()

    fun setDataAction(url: String) {
        if (URLUtil.isValidUrl(url))
            if (URLUtil.isHttpsUrl(url)) setData(url)
            else showToast.value = "запросы иного типа от https не поддерживаються"
        else showToast.value = "не верный формат ссылки"
    }

    private fun setData(url: String) {
        if (url.isNotEmpty()) {
            result.value = url
            isFinished.value = true
        }
    }

}