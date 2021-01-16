package com.example.pixabay.base.adapter

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<Item, ItemViewBinding : ViewBinding>(binding: ItemViewBinding) :
    RecyclerView.ViewHolder(binding.root){

    abstract fun bind(item: Item)
    open fun onItemClick(item: Item) {}
}