package com.hasankanal.kotlinsearchphotoapp.ui.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.binding.loadImage
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentDetailsBinding
import com.hasankanal.kotlinsearchphotoapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_details

    override fun getViewModel(): Class<DetailViewModel> = DetailViewModel::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.detailView = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var photos = arguments?.getParcelable<Photos>("photos")

        tv_detail_username.text = photos!!.user.name
        tv_detail_location.text = photos.user.location
        tv_detail_total_photo_number.text = photos.user.total_photos.toString()
        tvOverview.text = photos.user.bio


        val url = photos.urls.thumb
        loadImage(iv_detail_background,url)
        loadImage(iv_detail,url)


    }



}