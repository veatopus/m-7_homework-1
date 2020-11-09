package kg.geektech.ruslan.m_7_homework_1.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.geektech.ruslan.m_7_homework_1.core.BaseOnItemClick

class MainViewModel : ViewModel(), BaseOnItemClick {
    val dataListUrl = MutableLiveData<ArrayList<String>>()
    val imageForFullscreen = MutableLiveData<String>()
    val showToast = MutableLiveData<String>()

    init {
        addUrl()
    }

    private fun addUrl() {
        dataListUrl.value?.also {
            it.add("https://i.pinimg.com/originals/2d/ad/72/2dad721f57e4977a72fd90c67b6191b3.jpg")
            it.add("https://i.pinimg.com/originals/62/5b/82/625b824dc1cb31c58361655ff5e47f9b.jpg")
            it.add("https://images.pexels.com/photos/2893685/pexels-photo-2893685.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
            it.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg")
            it.add("https://oddstuffmagazine.com/wp-content/uploads/2020/05/Bow-Lake-in-Banff-National-Park.jpg")
            it.add("https://inspiredmagz.com/wp-content/uploads/2014/01/nature-01.jpg")
            it.add("https://wallpaperaccess.com/full/137242.jpg")
            it.add("https://webneel.com/daily/sites/default/files/images/daily/09-2013/17-most-amazing-photo-hight-sea-tide.jpg")
            it.add("https://instamoz.com/images/Amazing-HD-Amazing-World-Wallpaper.jpg")
            it.add("https://1.bp.blogspot.com/-XQuT9WokfJM/UXyImRGlhsI/AAAAAAAAtSs/jL_oGDXVCVk/s1600/Amazing+HD+Desktop+Wallpapers+(1).jpeg")
        }
    }

    override fun onItemClick(position: Int) {
       imageForFullscreen.value = dataListUrl.value?.get(position)
    }

    fun popupMenuDelete(position: Int) {
        if (position > 0) {
            dataListUrl.value?.removeAt(position)
        } else showToast.value = "первый элемент удалять нельзя"
    }
}