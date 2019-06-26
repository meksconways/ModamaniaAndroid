package com.modart.modamania.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.modart.modamania.main.MainActivityVM
import com.modart.modamania.util.ToolbarFont

abstract class BaseFragment : Fragment(){

    lateinit var mainVM: MainActivityVM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainVM = ViewModelProviders.of(activity!!)[MainActivityVM::class.java]
    }

    fun setToolbarTitle(title: String){
        mainVM.setToolbarTitle(title)
    }

    fun setToolbarFont(font: ToolbarFont){
        mainVM.setToolbarFontState(font)
    }

    fun setBottomBarState(state: Boolean){
        mainVM.setBottomState(state)
    }



}