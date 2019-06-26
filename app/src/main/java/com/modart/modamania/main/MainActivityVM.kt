package com.modart.modamania.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modart.modamania.util.ToolbarFont

class MainActivityVM : ViewModel() {

    private val bottomNavState = MutableLiveData<Boolean>(false)
    private val toolbarState = MutableLiveData<Boolean>(false)
    private val isFullScreen = MutableLiveData<Boolean>(true)
    private val mainScreenState = MutableLiveData<Boolean>(false)
    private val toolbarTitle = MutableLiveData<String>("Modamania")
    private val toolbarFontState = MutableLiveData<ToolbarFont>(ToolbarFont.N)

    fun getToolbarFontState(): LiveData<ToolbarFont> = toolbarFontState
    fun getMainScreenState(): LiveData<Boolean> = mainScreenState
    fun getBottomState(): LiveData<Boolean> = bottomNavState
    fun getToolbarState(): LiveData<Boolean> = toolbarState
    fun getIsFullScreen(): LiveData<Boolean> = isFullScreen
    fun getToolbarTitle(): LiveData<String> = toolbarTitle

    fun setToolbarFontState(state: ToolbarFont){
        this.toolbarFontState.value = state
    }

    fun setToolbarTitle(title: String){
        this.toolbarTitle.value = title
    }

    fun setMainScreenState(state: Boolean) {
        this.mainScreenState.value = state
    }


    fun setIsFullScreen(state: Boolean) {
        this.isFullScreen.value = state
    }

    fun setBottomState(state: Boolean) {
        this.bottomNavState.value = state
    }

    fun setToolbarState(state: Boolean) {
        this.toolbarState.value = state
    }


}