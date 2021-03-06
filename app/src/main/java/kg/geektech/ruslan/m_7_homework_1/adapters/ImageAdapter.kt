package kg.geektech.ruslan.m_7_homework_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.core.BaseOnItemClick
import kg.geektech.ruslan.m_7_homework_1.core.loadImage
import kg.geektech.ruslan.m_7_homework_1.interfaces.OnPopupMenuClickListener
import kotlinx.android.synthetic.main.item_image_recycler.view.*


data class ImageAdapter(val data: ArrayList<String>, val listener: BaseOnItemClick, val onPopupMenuClick: OnPopupMenuClickListener) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(data[position], listener, onPopupMenuClick)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(url: String, listener: BaseOnItemClick, onPopupMenuClick: OnPopupMenuClickListener) {
            loadImage(itemView.item_image_recyclerview_image_view_content, url)
            itemView.rootView.item_image_recyclerview_image_view_content.setOnClickListener { listener.onItemClick(adapterPosition) }
            itemView.rootView.item_image_recyclerview_button_popup_menu.setOnClickListener { onPopupMenuClick.onPopupMenuClick(it, adapterPosition) }
        }
    }
}