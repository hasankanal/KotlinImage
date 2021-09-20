package com.hasankanal.kotlinsearchphotoapp.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentRandomBinding
import com.hasankanal.kotlinsearchphotoapp.ui.search.SearchImagesAdapter
import kotlinx.android.synthetic.main.fragment_random.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.random.Random


class RandomFragment : BaseFragment<FragmentRandomBinding, RandomViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_random

    override fun getViewModel(): Class<RandomViewModel> = RandomViewModel::class.java

    private lateinit var randomImagesAdapter: RandomImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.randomView = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()

        swipeRandom.setOnRefreshListener {
            initView()
            observeLiveData()
            swipeRandom.isRefreshing = false
        }
    }

    private fun initAdapter(){
        randomImagesAdapter = context?.let { RandomImagesAdapter(it.applicationContext){
            var bundle = bundleOf("photos" to it)
            Navigation.findNavController(requireView())
                .navigate(R.id.action_randomFragment_to_detailsFragment,bundle)
        } }!!
        random_recycler.layoutManager = GridLayoutManager(context,3)
        random_recycler.adapter = randomImagesAdapter

    }

    private fun initView(){
        var rnd = (0..25).random()
        initAdapter()
        viewModel.getImageFromApi(rnd)
    }

    private fun observeLiveData(){
        viewModel.liveData.observe(viewLifecycleOwner, Observer{
            randomImagesAdapter.updateList(it!!)
            random_recycler.smoothScrollToPosition(0)
        })
    }


}