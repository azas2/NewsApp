package com.mala.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mala.newsapp.Adapter.NewsAdapter
import com.mala.newsapp.R
import com.mala.newsapp.ViewModels.NewsViewModel
import com.mala.newsapp.databinding.FragmentBreakingNewsBinding
import com.mala.newsapp.databinding.FragmentSavedNewsBinding
import com.mala.newsapp.ui.NewsActivity
import com.mala.newsapp.uitls.FragmentsNeedIt


class SavedNewsFragment : Fragment() ,FragmentsNeedIt{
    lateinit var binding: FragmentSavedNewsBinding
    override lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter:NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding= FragmentSavedNewsBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewmodel
        setupRecyclerView()
        newsAdapter.setOnItemClickListener {
            val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(it)
            findNavController().navigate(action)
        }

        // make it Drag and Swipe
        val itemTouchHelperCallback=object:ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                 Snackbar.make(view,"Successfully deleted article",Snackbar.LENGTH_SHORT).apply {
                     setAction("Undo"){
                         viewModel.getAddData(article)
                     }
                         .show()
                 }
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.reSaved)
        }

        // here call list of save
        viewModel.getSavedData().observe(viewLifecycleOwner, Observer { Article ->
            newsAdapter.differ.submitList(Article)
        })
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.apply {
            reSaved.adapter = newsAdapter
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


