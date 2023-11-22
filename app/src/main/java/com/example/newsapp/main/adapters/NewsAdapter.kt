package com.example.newsapp.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.FragmentBreakingNewsBinding
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.main.models.Article


class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var articleList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BreakingNewsItemViewHolder(
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = articleList[position]
        (holder as BreakingNewsItemViewHolder).apply {
            bind(item)
            itemView.setOnClickListener { onItemClickListener?.let { it(item) } }

        }

    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return articleList.size

    }


    fun setData(item: List<Article>) {
        this.articleList = item
        notifyDataSetChanged()
    }
}