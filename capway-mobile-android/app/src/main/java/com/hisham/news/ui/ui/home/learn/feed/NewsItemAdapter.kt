package com.hisham.news.ui.ui.home.learn.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hisham.news.R
import com.hisham.news.pojo.news.Result
import com.hisham.news.utils.DateParser
import com.hisham.news.utils.OnItemClick
import com.hisham.news.utils.bindings.BindingUtils


class NewsItemAdapter(private val context: Context,private val itemList:ArrayList<Result>,val onItemClick: OnItemClick)
    : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val view = mInflater.inflate(R.layout.row_news_item, parent, false)
           return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsItemAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        if(item != null)
        {
            holder.author!!.text = item.byline
            holder.date!!.text = DateParser.parseDateForFeed(item.updated_date)
            holder.title!!.text = item.title
            if (!item.multimedia.isNullOrEmpty())
            {
                BindingUtils.setImageSrc(holder.newsImage!!, item.multimedia!![2].url)
            }
        }
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var author: TextView? = null
        var date: TextView? = null
        var newsImage: ImageView? = null

        private fun registerView() {
            title = view.findViewById(R.id.title)
            author = view.findViewById(R.id.author)
            date = view.findViewById(R.id.date)
            newsImage = view.findViewById(R.id.news_image)
        }

        private fun registerListener() {
            view.setOnClickListener { handleClickListener() }
        }

        fun handleClickListener() {
            onItemClick.onItemClick(itemList.get(adapterPosition))

        }

        init {
            registerView()
            registerListener()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}