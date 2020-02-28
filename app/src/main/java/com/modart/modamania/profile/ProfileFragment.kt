package com.modart.modamania.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.modart.modamania.R
import com.modart.modamania.base.BaseFragment
import com.modart.modamania.util.ToolbarFont
import com.modart.modamania.util.invisible
import com.modart.modamania.util.loadImage
import com.modart.modamania.util.visible
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : BaseFragment() {


    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        setToolbarTitle("Profil")
        setToolbarFont(ToolbarFont.OS)
        observeVM()

        rv_profile.adapter = ProfilePostAdapter(viewModel,this)
        rv_profile.layoutManager = LinearLayoutManager(context)

    }

    private fun observeVM() {
        viewModel.getPageLoading().observe(this, Observer {
            if (it) {
                rv_profile.showShimmerAdapter()
                lyt_profile.invisible()
                rv_shimmer.showShimmerAdapter()
            } else {
                rv_profile.hideShimmerAdapter()
                lyt_profile.visible()
                rv_shimmer.hideShimmerAdapter()
            }
        })

        viewModel.getProfileData().observe(this, Observer {
            it?.let { model ->

                if (model.user.user_folder != "default.jpeg"){
                    imgProfile.loadImage(model.user.user_folder)
                }

                txtFullName.text = model.user.name_surname
                txtUsername.text = model.user.username
                followingCount.text = "${model.user.following_count}"
                postCount.text = "${model.user.total_post_count}"
                followersCount.text = "${model.user.follower_count}"

            }
        })
    }

}
