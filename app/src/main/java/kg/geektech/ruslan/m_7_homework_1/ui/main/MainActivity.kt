package kg.geektech.ruslan.m_7_homework_1.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.adapters.ImageAdapter
import kg.geektech.ruslan.m_7_homework_1.core.BaseOnItemClick
import kg.geektech.ruslan.m_7_homework_1.ui.edit.EditActivity
import kg.geektech.ruslan.m_7_homework_1.ui.image_fullscreen.ImageFullscreenActivity
import kotlinx.android.synthetic.main.activity_main.*


const val EDIT_ACTIVITY = 41

class MainActivity : AppCompatActivity(), BaseOnItemClick {
    private val listImageUrl = ArrayList<String>()
    private val adapter = ImageAdapter(listImageUrl, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setArg()
        onEditAction()
        supportActionBar?.title = "main"
    }

    private fun onEditAction() {
        activity_main_fab_edit.setOnClickListener { onEdit() }
    }

    private fun onEdit() {
        startActivityForResult(Intent(this, EditActivity::class.java), EDIT_ACTIVITY)
    }

    private fun setArg() {
        addUrl()
        activity_main_recyclerview_image.adapter = adapter
        activity_main_recyclerview_image.addItemDecoration(
            DividerItemDecoration(
                activity_main_recyclerview_image.context,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == EDIT_ACTIVITY && data != null) {
            data.getStringExtra(EditActivity.RESULT_CODE)?.let { addDataInRecycler(it) }
        }
    }


    private fun addDataInRecycler(url: String){
        listImageUrl.add(url)
        adapter.notifyDataSetChanged()
    }

    private fun addUrl() {
        listImageUrl.add("https://i.pinimg.com/originals/2d/ad/72/2dad721f57e4977a72fd90c67b6191b3.jpg")
        listImageUrl.add("https://i.pinimg.com/originals/62/5b/82/625b824dc1cb31c58361655ff5e47f9b.jpg")
        listImageUrl.add("https://images.pexels.com/photos/2893685/pexels-photo-2893685.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
        listImageUrl.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg")
        listImageUrl.add("https://oddstuffmagazine.com/wp-content/uploads/2020/05/Bow-Lake-in-Banff-National-Park.jpg")
        listImageUrl.add("https://inspiredmagz.com/wp-content/uploads/2014/01/nature-01.jpg")
        listImageUrl.add("https://wallpaperaccess.com/full/137242.jpg")
        listImageUrl.add("https://webneel.com/daily/sites/default/files/images/daily/09-2013/17-most-amazing-photo-hight-sea-tide.jpg")
        listImageUrl.add("https://instamoz.com/images/Amazing-HD-Amazing-World-Wallpaper.jpg")
        listImageUrl.add("https://1.bp.blogspot.com/-XQuT9WokfJM/UXyImRGlhsI/AAAAAAAAtSs/jL_oGDXVCVk/s1600/Amazing+HD+Desktop+Wallpapers+(1).jpeg")
    }

    override fun onItemClick(position: Int) {
        ImageFullscreenActivity.newInstance(this, listImageUrl[position])
    }
}
