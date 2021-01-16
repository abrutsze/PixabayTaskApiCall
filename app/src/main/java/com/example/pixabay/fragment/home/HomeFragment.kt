package com.example.pixabay.fragment.home

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pixabay.MainActivity
import com.example.pixabay.R
import com.example.pixabay.base.FragmentBaseMVVM
import com.example.pixabay.base.utils.viewBinding
import com.example.pixabay.databinding.FragmentHomeBinding
import com.example.pixabay.fragment.imagedetail.ImageDetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : FragmentBaseMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModel()
    override val binding: FragmentHomeBinding by viewBinding()
    private var imagesAdapter = ImagesAdapter {
        val args = bundleOf(ImageDetailFragment.EXTRA_IMAGE_DETAIL_DATA_KEY to it)
        navigateFragment(
            R.id.action_navigationHome_to_navigationImageDetail,
            args
        )
    }

    override fun initView() {
        with(binding) {
            rvData.apply {
                context?.let {
                    layoutManager =
                        GridLayoutManager(it, 2)
                    setHasFixedSize(true)
                    adapter = imagesAdapter
                }
            }
        }
    }

    override fun observes() {
        observe(viewModel.getDataModelImagesItemList) {
            imagesAdapter.submitList(it)
        }
        observe(viewModel.loadingData) {
            if (it)
                vLoadingData.visibility = View.VISIBLE
            else
                vLoadingData.visibility = View.GONE
        }
    }

    override fun navigateUp() {
        (activity as MainActivity).finish()
    }
}