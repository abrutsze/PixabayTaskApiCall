package com.example.pixabay.fragment.imagedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.entity.localmodels.PixaBayListItem
import com.example.pixabay.R
import com.example.pixabay.base.FragmentBaseMVVM
import com.example.pixabay.base.utils.viewBinding
import com.example.pixabay.base.viewmodel.BaseViewModel
import com.example.pixabay.databinding.FragmentImageDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageDetailFragment : FragmentBaseMVVM<BaseViewModel, FragmentImageDetailBinding>() {
    companion object {
        const val EXTRA_IMAGE_DETAIL_DATA_KEY = "imageDetail"
    }

    override val viewModel: BaseViewModel by viewModel()
    override val binding: FragmentImageDetailBinding by viewBinding()
    private var data: PixaBayListItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = arguments?.getSerializable(EXTRA_IMAGE_DETAIL_DATA_KEY) as PixaBayListItem
    }

    override fun initView() {
        with(binding) {
            data?.let { data ->
                Glide.with(root.context)
                    .load(data.detailImageURL)
                    .into(icon)
                size.text = String.format("%s %d", getString(R.string.size), data.imageSize)
                type.text = String.format("%s %s", getString(R.string.type), data.type)
                tag.text = String.format("%s %s", getString(R.string.tag), data.tags)
                ownerImage.text = String.format("%s %s", getString(R.string.image_owner), data.user)
                views.text = String.format("%s %d", getString(R.string.views), data.views)
                likes.text = String.format("%s %d", getString(R.string.likes), data.likes)
                comments.text = String.format("%s %d", getString(R.string.comments), data.comments)
                favorites.text = String.format("%s %d", getString(R.string.favorites), data.favorites)
                downloads.text = String.format("%s %d", getString(R.string.downloads), data.downloads)
            }
        }
    }

    override fun navigateUp() {
        navigateBackStack()
    }
}