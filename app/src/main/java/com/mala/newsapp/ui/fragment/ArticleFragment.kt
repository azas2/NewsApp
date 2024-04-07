package com.mala.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mala.newsapp.Adapter.NewsAdapter
import com.mala.newsapp.R
import com.mala.newsapp.ViewModels.NewsViewModel
import com.mala.newsapp.databinding.FragmentArticalNewBinding
import com.mala.newsapp.ui.NewsActivity
import com.mala.newsapp.uitls.FragmentsNeedIt
import com.mala.newsapp.uitls.Resource


class ArticleFragment : Fragment(),FragmentsNeedIt {
    lateinit var binding: FragmentArticalNewBinding
    override lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val args:ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentArticalNewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewmodel
        val article=args.article
        binding.articleWebView.apply {
             webViewClient=WebViewClient()
            article.url?.let { loadUrl(it) }

        }
        binding.fab.setOnClickListener {
        viewModel.getAddData(article)
            Snackbar.make(view,"Article saved Successfly",Snackbar.LENGTH_SHORT).show()
        }


    }


    override fun onResume() {
        super.onResume()
        requireActivity().actionBar?.show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().actionBar?.show()
    }

}




