package com.example.pixabay.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.entity.localmodels.PixaBayListItem
import com.example.pixabay.R
import com.example.pixabay.base.adapter.BaseAdapter
import com.example.pixabay.base.adapter.BaseViewHolder

import com.example.pixabay.databinding.ItemImagesBinding

class ImagesAdapter(val goToDetail:(item:PixaBayListItem)->Unit) :
    BaseAdapter<ViewBinding, PixaBayListItem, BaseViewHolder<PixaBayListItem, ViewBinding>>(
        EventsDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<PixaBayListItem, ViewBinding> {
        return ItemViewHolder(
            ItemImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ItemViewHolder(private val binding: ItemImagesBinding) :
        BaseViewHolder<PixaBayListItem, ViewBinding>(binding) {
        override fun bind(item: PixaBayListItem) {
            with(binding){
                Glide.with(root.context)
                    .load(item.previewURL)
                    .into(icon)
                ownerImage.text=item.user
            }
        }

        override fun onItemClick(item: PixaBayListItem) {
            goToDetail.invoke(item)
        }
    }
}

internal class EventsDiffCallback : DiffUtil.ItemCallback<PixaBayListItem>() {
    override fun areItemsTheSame(oldItem: PixaBayListItem, newItem: PixaBayListItem): Boolean =
        oldItem.previewURL == newItem.previewURL

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PixaBayListItem, newItem: PixaBayListItem): Boolean =
        oldItem == newItem

}

