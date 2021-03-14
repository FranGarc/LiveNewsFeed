package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.francgar.livenewsfeed.ui.NewsActivity
import com.francgar.livenewsfeed.ui.NewsViewModel
import com.francgar.livenewsfeed.util.Constants

abstract class NewsBaseFragment(layout: Int) : Fragment(layout) {
    lateinit var viewModel: NewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }









}