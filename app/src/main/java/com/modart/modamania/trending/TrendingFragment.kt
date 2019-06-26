package com.modart.modamania.trending

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.modart.modamania.R
import com.modart.modamania.base.BaseFragment
import com.modart.modamania.util.ToolbarFont

class TrendingFragment : BaseFragment() {

    companion object {
        fun newInstance() = TrendingFragment()
    }

    private lateinit var viewModel: TrendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.trending_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingViewModel::class.java)
        setToolbarTitle("Trendler")
        setToolbarFont(ToolbarFont.OS)
    }

}
