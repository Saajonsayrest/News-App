package com.example.newsapp.main.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentArticleNewsBinding
import com.example.newsapp.main.models.Article
import com.example.newsapp.main.ui.NewsActivity
import com.example.newsapp.main.ui.NewsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ArticleNewsFragment : Fragment(R.layout.fragment_article_news) {


    lateinit var viewModel: NewsViewModel

    private var _binding: FragmentArticleNewsBinding? = null
    private val binding get() = _binding!!
    private var job: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        job = viewLifecycleOwner.lifecycleScope.launch {
            _binding = FragmentArticleNewsBinding.bind(view)
            binding.webView.apply {
                webViewClient = WebViewClient()
                val article = arguments?.getParcelable<Article>("article")
                article?.url?.let {
                    Log.d("abcd", it)
                    loadUrl(article.url)
                }
            }
        }

    }
}