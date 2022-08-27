package com.hisham.news.ui.ui.home.learn.feed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hisham.news.BR
import com.hisham.news.R
import com.hisham.news.base.BaseFragment
import com.hisham.news.databinding.NewsFragmentBinding
import com.hisham.news.pojo.news.Result
import com.hisham.news.utils.OnItemClick
import kotlinx.android.synthetic.main.news_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class NewsFragment : BaseFragment<NewsFragmentBinding, NewsViewModel>(), OnItemClick {

    override fun getLayoutId(): Int = com.hisham.news.R.layout.news_fragment
    override fun getViewModel(): NewsViewModel = newsViewModel
    private val newsViewModel: NewsViewModel by viewModel()

    private var itemAdapter: NewsItemAdapter? = null

    private lateinit var newsList: ArrayList<com.hisham.news.pojo.news.Result>

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpObservers()
        newsViewModel.getNewsFeed()
        onBackPress()
    }

    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(context)
        news_item.layoutManager = linearLayoutManager
    }
    private fun setUpObservers() {
        newsViewModel.uiState.observe(viewLifecycleOwner, Observer {
            val dataState = it ?: return@Observer
            progressBar.visibility =
                if (dataState.showProgress) View.VISIBLE else View.GONE
            if (dataState.data != null && !dataState.data.consumed)
                dataState.data.consume()?.let { data ->
                    newsList = data.results as ArrayList<Result>

                    if (newsList.size == 0) {
                        no_Results.visibility = View.VISIBLE
                        news_item.visibility = View.GONE
                    } else {
                        news_item.visibility = View.VISIBLE
                        no_Results.visibility = View.GONE
                        setUpNewsRecycler()
                    }
                }
            if (dataState.error != null && !dataState.error.consumed)
                dataState.error.consume()?.let { errorMessage ->
                    //  handle error
                    Log.d("ERROR_", errorMessage.toString())
                }
        })
    }
    private fun setUpNewsRecycler() {

        if (itemAdapter == null)
            itemAdapter = context?.let { NewsItemAdapter(it,newsList,this) }
        news_item!!.adapter = itemAdapter
    }

    companion object {
        val TAG = NewsFragment::class.java.simpleName

        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = NewsFragment()
            activity.supportFragmentManager.beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }

    private fun onBackPress()
    {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()

        view?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.getAction() === KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        requireActivity().finish()
                        return true
                    }
                }
                return false
            }
        })
    }
    override fun onItemClick(any: Any) {
        val itemDetail = any as Result
        NewsDetailFragment.start(requireActivity(), R.id.main_screen_container,itemDetail)
    }
}