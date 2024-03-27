package com.mala.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mala.newsapp.R
import com.mala.newsapp.databinding.FragmentBreakingNewsBinding
import com.mala.newsapp.databinding.FragmentSavedNewsBinding


class SavedNewsFragment : Fragment() {
    lateinit var binding: FragmentSavedNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSavedNewsBinding.inflate(inflater,container,false)
        return binding.root
    }


}