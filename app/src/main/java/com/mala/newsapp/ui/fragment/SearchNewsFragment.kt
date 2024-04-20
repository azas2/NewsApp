package com.mala.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mala.newsapp.Adapter.NewsAdapter
import com.mala.newsapp.ViewModels.NewsViewModel
import com.mala.newsapp.databinding.FragmentSearchNewsFragmentBinding
import com.mala.newsapp.ui.NewsActivity
import com.mala.newsapp.uitls.FragmentsNeedIt
import com.mala.newsapp.uitls.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint

class SearchNewsFragment() : Fragment(),FragmentsNeedIt {
    lateinit var binding: FragmentSearchNewsFragmentBinding
    override lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    var job: Job?=null
    val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchNewsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewmodel
        setUpAdapter()
        binding.tfSearch.addTextChangedListener{ editable ->
            job?.cancel()
            job= MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()){

                        viewModel.SearchOfNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
               is Resource.Success ->{
                   response.data?.let { searchResponse->
                        newsAdapter.differ.submitList(searchResponse.articles)
                       Log.d(TAG,"this our search ${searchResponse.articles}")
                    }
               }
                is Resource.Error ->{
                    response.msg?.let {
                        Log.e(TAG, "catch Fragment Error")

                    }
                }
                is Resource.Loading ->{
                }
            }
        })
        newsAdapter.setOnItemClickListener {
            val action = SearchNewsFragmentDirections.actionSearchNewsFragemnt2ToArticleFragment(it)
            findNavController().navigate(action)
        }

    }


    private fun setUpAdapter(){
        newsAdapter= NewsAdapter()
        binding.apply {
            reSearch.adapter=newsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

    }


}