package com.francgar.livenewsfeed.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.francgar.livenewsfeed.R
import com.francgar.livenewsfeed.ui.NewsActivity
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : NewsBaseFragment(R.layout.fragment_article) {

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

}