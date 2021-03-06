package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.francgar.livenewsfeed.ui.NewsActivity
import com.francgar.livenewsfeed.ui.NewsViewModel

abstract class NewsBaseFragment(layout: Int) : Fragment(layout) {
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}