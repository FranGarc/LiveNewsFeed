package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.francgar.livenewsfeed.R
import com.francgar.livenewsfeed.adapters.NewsAdapter
import com.francgar.livenewsfeed.util.CLog
import com.francgar.livenewsfeed.util.Constants.COUNTRY_CODE
import com.francgar.livenewsfeed.util.Constants.QUERY_PAGE_SIZE
import com.francgar.livenewsfeed.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : PaginableNewsBaseFragment(R.layout.fragment_breaking_news) {
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
            findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment, bundle)
        }


        viewModel.breakingNews.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar(paginationProgressBar)
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2 // +1 due to rounding off of integer division, +1 to avoid empty last page
                        isLastPage = viewModel.breakingNewsPage == totalPages
                        if (isLastPage) {
                            rvBreakingNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar(paginationProgressBar)
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG).show()
                        CLog.e(message, Exception(message))
                    }
                }
                is Resource.Loading -> {
                    showProgressBar(paginationProgressBar)
                }
            }
        })
    }


    override fun nextPage() {
        viewModel.getBreakingNews(COUNTRY_CODE)
    }

    private fun setupRecyclerView() {
        CLog.v("BreakingNewsFragment.setupRecyclerView()")
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            CLog.v("BreakingNewsFragment.setupRecyclerView() adapter set: $adapter")
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }
}