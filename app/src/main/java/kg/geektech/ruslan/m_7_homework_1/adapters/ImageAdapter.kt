package kg.geektech.ruslan.m_7_homework_1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import kg.geektech.ruslan.m_7_homework_1.R
import kg.geektech.ruslan.m_7_homework_1.core.BaseOnItemClick
import kg.geektech.ruslan.m_7_homework_1.core.loadImage
import kotlinx.android.synthetic.main.item_image_recycler.view.*

data class ImageAdapter(val data: ArrayList<String>, val listener: BaseOnItemClick) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_recycler, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(data[position], listener)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(url: String, listener: BaseOnItemClick) {
            loadImage(itemView.item_image_recyclerview_image_view_content, url)
            itemView.rootView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}