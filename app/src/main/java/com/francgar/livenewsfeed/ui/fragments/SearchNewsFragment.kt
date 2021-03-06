package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.francgar.livenewsfeed.R
import com.francgar.livenewsfeed.adapters.NewsAdapter
import com.francgar.livenewsfeed.ui.NewsActivity
import com.francgar.livenewsfeed.util.CLog
import com.francgar.livenewsfeed.util.Constants.SEARCH_NEWS_TIME_DELAY
import com.francgar.livenewsfeed.util.Resource

import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : NewsBaseFragment(R.layout.fragment_search_news) {

    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        newsAdapter.setOnItemClickListener { article ->
            CLog.v("BreakingNewsFragment.onItemClickListener(article: $article)")

            val bundle = Bundle()
            bundle.apply {
                putSerializable("article", article)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
        }


        viewModel = (activity as NewsActivity).viewModel

        var job: Job? = null

        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    val searchQuery = editable.toString()
                    if (searchQuery.isNotEmpty()) {
                        viewModel.searchNews(searchQuery)
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
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
        CLog.v("SearchNewsFragment.setupRecyclerView()")
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            CLog.v("SearchNewsFragment.setupRecyclerView() adapter set: $adapter")
            layoutManager = LinearLayoutManager(activity)
        }
    }
}