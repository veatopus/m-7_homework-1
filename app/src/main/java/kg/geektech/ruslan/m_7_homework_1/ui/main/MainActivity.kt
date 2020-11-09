package kg.geektech.ruslan.m_7_homework_1.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.adapters.ImageAdapter
import kg.geektech.ruslan.m_7_homework_1.core.showToast
import kg.geektech.ruslan.m_7_homework_1.interfaces.OnPopupMenuClickListener
import kg.geektech.ruslan.m_7_homework_1.ui.edit.EditActivity
import kg.geektech.ruslan.m_7_homework_1.ui.image_fullscreen.ImageFullscreenActivity
import kotlinx.android.synthetic.main.activity_main.*

const val EDIT_ACTIVITY = 41
class MainActivity : AppCompatActivity(), OnPopupMenuClickListener{
    private val listImageUrl = ArrayList<String>()
    private var mViewModel: MainViewModel? = null
    private var adapter: ImageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setArg()
        observe()
        supportActionBar?.title = "main"
    }

    private fun observe() {
        mViewModel?.dataListUrl?.observe(this, Observer { setDataInRecycler(it) })
        mViewModel?.imageForFullscreen?.observe(this, Observer {
            ImageFullscreenActivity.newInstance(this, it)
        })
        mViewModel?.showToast?.observe(this, Observer { showToast(this, it) })

    }

    private fun init() {
        mViewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        adapter = ImageAdapter(listImageUrl, mViewModel!!, this)

    }

    private fun onEditAction() {
        activity_main_fab_edit.setOnClickListener { onEdit() }
    }

    private fun onEdit() {
        startActivityForResult(Intent(this, EditActivity::class.java), EDIT_ACTIVITY)
    }

    private fun setArg() {
        activity_main_recyclerview_image.adapter = adapter
        activity_main_recyclerview_image.addItemDecoration(
            DividerItemDecoration(
                activity_main_recyclerview_image.context,
                DividerItemDecoration.VERTICAL
            )
        )
        onEditAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == EDIT_ACTIVITY && data != null) {
            data.getStringExtra(EditActivity.RESULT_CODE)?.let { addDataInRecycler(it) }
        }
    }


    private fun addDataInRecycler(url: String){
        listImageUrl.add(url)
        adapter?.notifyDataSetChanged()
    }

    private fun setDataInRecycler(data: List<String>){
        listImageUrl.clear()
        listImageUrl.addAll(data)
        adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NonConstantResourceId")
    private fun showPopupMenu(v: View, position: Int) {
        val popupMenu = PopupMenu(this, v)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu
            .setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete -> {
                        mViewModel?.popupMenuDelete(position)
                        adapter?.notifyItemRemoved(position)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.no -> return@setOnMenuItemClickListener true
                    else -> return@setOnMenuItemClickListener false
                }
            }
        popupMenu.show()
    }

    override fun onPopupMenuClick(v: View?, position: Int) {
        v?.let { showPopupMenu(it, position) }
    }
}