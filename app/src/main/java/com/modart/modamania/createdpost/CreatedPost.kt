package com.modart.modamania.createdpost

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.modart.modamania.R

class CreatedPost : Fragment() {

    companion object {
        fun newInstance() = CreatedPost()
    }

    private lateinit var viewModel: CreatedPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.created_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreatedPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
