package com.mala.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mala.newsapp.R
import com.mala.newsapp.databinding.FragmentSavedNewsBinding
import com.mala.newsapp.databinding.FragmentSearchNewsFragmentBinding


class SearchNewsFragment : Fragment() {
    lateinit var binding: FragmentSearchNewsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchNewsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

}