package com.example.newsapp.main.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.main.db.ArticleDatabase
import com.example.newsapp.main.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Find the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)

        // Retrieve the BottomNavigationView from the layout
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Set up navigation with the NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment!!.findNavController())


        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

    }
}
