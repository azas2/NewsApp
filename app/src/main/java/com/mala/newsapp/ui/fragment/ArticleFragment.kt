package com.mala.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mala.newsapp.R
import com.mala.newsapp.databinding.FragmentArticalNewBinding


class ArticleFragment : Fragment() {
    lateinit var binding: FragmentArticalNewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentArticalNewBinding.inflate(inflater,container,false)
        return binding.root
    }

}