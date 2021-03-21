package com.nishant.currentaffairs.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.nishant.currentaffairs.R
import com.nishant.currentaffairs.databinding.FragmentArticleBinding
import com.nishant.currentaffairs.ui.MainViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private lateinit var binding: FragmentArticleBinding
    private val viewModel by viewModels<MainViewModel>()
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            try {
                loadUrl(article.urlToImage!!)
            } catch (e: KotlinNullPointerException) {
                Toast.makeText(activity, "Could not load image", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}