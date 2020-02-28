package com.modart.modamania.feed

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.modart.modamania.R
import com.modart.modamania.base.BaseFragment
import com.modart.modamania.util.ToolbarFont
import kotlinx.android.synthetic.main.feed_fragment.*

class FeedFragment : BaseFragment() {


    companion object {
        fun newInstance() = FeedFragment()
    }

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(FeedViewModel::class.java)
        setToolbarTitle("Modamania")
        setToolbarFont(ToolbarFont.N)
        observeVM()

        rv_feed.adapter = FeedAdapter(viewModel,this)
        rv_feed.layoutManager = LinearLayoutManager(context)



    }

    private fun observeVM() {
        viewModel.getPageLoading().observe(this, Observer {
            if (it) rv_feed.showShimmerAdapter() else rv_feed.hideShimmerAdapter()
        })
    }

}
