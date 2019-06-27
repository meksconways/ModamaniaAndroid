package com.modart.modamania.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.modart.modamania.main.MainActivityVM
import com.modart.modamania.util.ToolbarFont
import com.modart.modamania.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment(){

    private lateinit var mainVM: MainActivityVM
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.getAppComponent().inject(this)
    }

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