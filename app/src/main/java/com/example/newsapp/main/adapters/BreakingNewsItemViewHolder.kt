package com.example.newsapp.main.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.main.models.Article

class BreakingNewsItemViewHolder(private val breakingNewsItemBinding: ItemArticlePreviewBinding) :
    RecyclerView.ViewHolder(breakingNewsItemBinding.root) {


    fun bind(
        item: Article
    ) {
        breakingNewsItemBinding.apply {
            Glide.with(itemView).load(item.urlToImage).into(ivArticleImage)
            tvSource.text = item.source.name
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPublishedAt.text = item.publishedAt
        }
    }
}