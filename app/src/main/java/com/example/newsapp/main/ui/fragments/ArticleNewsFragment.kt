package com.example.newsapp.main.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.main.ui.NewsActivity
import com.example.newsapp.main.ui.NewsViewModel


class ArticleNewsFragment : Fragment(R.layout.fragment_article_news) {
    lateinit var viewModel: NewsViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}