package com.mala.newsapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mala.newsapp.databinding.ItemArticalePreviewBinding
import com.mala.newsapp.model.Article

class NewsAdapter :RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    // make myview
    inner class ViewHolder(val binding:ItemArticalePreviewBinding):RecyclerView.ViewHolder(binding.root)
    // make differ class to check old list with newList
    private val differCallback=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
          return oldItem.url==newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem==newItem
        }

    }
    // here give the list
    val differ=AsyncListDiffer(this,differCallback)
    fun submitList(list:List<Article>){
        val filterList=list.filter { it.urlToImage!=null}
        differ.submitList(filterList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemArticalePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val artical =differ.currentList[position]

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(artical.urlToImage)
                .into(holder.binding.articleImage)
            tvSource.text=artical.source?.name
            tvTitle.text=artical?.title
            tvDescription.text=artical?.description
            tvpublishedAt.text=artical?.publishedAt
            root.setOnClickListener {
                onItemClickListener?.let { it(artical)}
            }
        }



    }
    //{ article:Article ->
// make listener to when click to specific article open to web view
    private var onItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener

    }


}