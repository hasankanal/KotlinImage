package com.hasankanal.kotlinsearchphotoapp.ui.search

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentSearchBinding
import com.hasankanal.kotlinsearchphotoapp.utils.Constants


import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java


    private lateinit var searchImagesAdapter: SearchImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.viewSearch = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeLiveData()
        searchViewControl()
        iv_search.setOnClickListener {
            searchViewControl()
        }

        searchSwipeRefresh.setOnRefreshListener {
            var rnd = (0..25).random()
            initAdapter()
            viewModel.searchImages(Constants.searchImage,rnd)
            observeLiveData()
            searchSwipeRefresh.isRefreshing = false
        }



    }

    private fun observeLiveData(){
        viewModel.liveData.observe(viewLifecycleOwner, Observer{
            searchImagesAdapter.updateList(it!!)
            search_recycler.smoothScrollToPosition(0)
        })
    }

    private fun initAdapter(){

        searchImagesAdapter = context?.let { SearchImagesAdapter(it.applicationContext){
            var bundle = bundleOf("photos" to it)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_searchFragment_to_detailsFragment,bundle)
        } }!!
        search_recycler.layoutManager = GridLayoutManager(context,3)
        search_recycler.adapter = searchImagesAdapter


    }

    private fun initView(){
        initAdapter()
        et_search.text.toString()
        btn_search.setOnClickListener { view ->
            if(!et_search.text.toString().equals("")){
                Constants.searchImage = et_search.text.toString()
                viewModel.searchImages(et_search.text.toString(),1)
                searchViewControl()
                et_search.text.clear()
            }
        }
    }

    fun searchViewControl(){
        val paramsMatch = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        val paramsWrap = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        if (et_search.visibility == View.VISIBLE){
            et_search.visibility = View.GONE
            btn_search.visibility = View.GONE
            TransitionManager.beginDelayedTransition(cv_search, AutoTransition())
            cv_search.layoutParams = paramsWrap
        } else if (et_search.visibility == View.GONE){
            et_search.visibility = View.VISIBLE
            btn_search.visibility = View.VISIBLE
            TransitionManager.beginDelayedTransition(cv_search, AutoTransition())
            cv_search.layoutParams = paramsMatch
        }
    }


}