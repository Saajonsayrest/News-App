package com.example.newsapp.main.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleNewsBinding
import com.example.newsapp.main.models.Article
import com.example.newsapp.main.ui.NewsActivity
import com.example.newsapp.main.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ArticleNewsFragment : Fragment(R.layout.fragment_article_news) {

    private lateinit var viewModel: NewsViewModel

    private var _binding: FragmentArticleNewsBinding? = null
    private val binding get() = _binding!!
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        _binding = FragmentArticleNewsBinding.bind(view)

        // Show loading indicator while the WebView is loading
        binding.progressBar.visibility = View.VISIBLE

        job = viewLifecycleOwner.lifecycleScope.launch {
            _binding = FragmentArticleNewsBinding.bind(view)
            binding.webView.apply {
                webViewClient = WebViewClient()

                // Use WebChromeClient to track loading progress
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)

                        // Hide the loading indicator when the page is fully loaded
                        if (newProgress == 100) {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }

                val article = arguments?.getParcelable<Article>("article")
                article?.url?.let {
                    Log.d("abcd", it)
                    loadUrl(it)
                }
                binding.fab.setOnClickListener {
                    if (article != null) {
                        viewModel.saveArticle(article)
                        Snackbar.make(view, "Article Saved Successfully", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }
}