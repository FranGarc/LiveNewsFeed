package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.francgar.livenewsfeed.R
import com.francgar.livenewsfeed.adapters.NewsAdapter
import com.francgar.livenewsfeed.util.CLog
import com.francgar.livenewsfeed.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : NewsBaseFragment(R.layout.fragment_breaking_news) {
    lateinit var newsAdapter: NewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar(paginationProgressBar)
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar(paginationProgressBar)
                    response.message?.let {
                        CLog.e(it, Exception(it))
                    }
                }
                is Resource.Loading -> {
                    showProgressBar(paginationProgressBar)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        CLog.v("BreakingNewsFragment.setupRecyclerView()")
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            CLog.v("BreakingNewsFragment.setupRecyclerView() adapter set: $adapter")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}