package com.hasankanal.kotlinsearchphotoapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentProfileBinding
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentProfileEndBinding
import com.hasankanal.kotlinsearchphotoapp.ui.random.RandomImagesAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.photoRecycler
import kotlinx.android.synthetic.main.fragment_profile_end.*
import kotlinx.android.synthetic.main.fragment_random.*

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    val userName = String


    override fun getLayoutRes(): Int = R.layout.fragment_profile

    override fun getViewModel(): Class<ProfileViewModel> = ProfileViewModel::class.java

    private lateinit var profileAdapter: ProfileAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.profileView = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  initView()
        observeLiveData()

       */


    }

    private fun observeLiveData(){
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            profileAdapter.updateList(it!!)
            photoRecycler.smoothScrollToPosition(0)
        })
    }

    private fun initView(){
        initAdapter()
        viewModel.getAllImages()
    }

    private fun initAdapter(){
        profileAdapter = context?.let {
            ProfileAdapter(it.applicationContext){
                Toast.makeText(requireContext(),"adapter icinde",Toast.LENGTH_SHORT).show()
            }
        }!!
        photoRecycler.layoutManager = GridLayoutManager(context,3)
        photoRecycler.adapter = profileAdapter
    }




}