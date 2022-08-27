package com.hisham.news.ui.ui.home.learn.feed

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.hisham.news.BR
import com.hisham.news.R
import com.hisham.news.base.BaseFragment
import com.hisham.news.databinding.NewsDetailFragementBinding
import com.hisham.news.pojo.news.Result
import com.hisham.news.utils.Constants
import com.hisham.news.utils.DateParser
import com.hisham.news.utils.bindings.BindingUtils
import kotlinx.android.synthetic.main.news_detail_fragement.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsDetailFragment : BaseFragment<NewsDetailFragementBinding, NewsViewModel>() {


    override fun getLayoutId(): Int = com.hisham.news.R.layout.news_detail_fragement
    override fun getViewModel(): NewsViewModel = newsViewModel
    private val newsViewModel: NewsViewModel by viewModel()

    lateinit var newsDetail: Result

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        initView()
    }
    private fun getIntentData()
    {
        newsDetail = arguments?.getParcelable<Result>(Constants.NEWS_DETAIL)!!
    }
    private fun setData()
    {
        if (!newsDetail.multimedia.isNullOrEmpty())
            {
                BindingUtils.setImageSrc(mainImage, newsDetail.multimedia!![1].url)
            }
            meaningTitle.text = Html.fromHtml(newsDetail.title)
        author.text = newsDetail.byline
            dateFeedDetail.text = DateParser.parseDateForFeed(newsDetail.updated_date)
            tvDetail.text = Html.fromHtml(newsDetail.abstract)
    }

    private fun initView() {
            back.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            seeMore.setOnClickListener {
                newsDetail.short_url?.let { it1 ->
                    WebviewFragment.start(requireActivity(), R.id.main_screen_container,
                        it1
                    )
                }
            }
    }

    companion object {
        val TAG = NewsDetailFragment::class.java.simpleName

        fun start(activity: FragmentActivity, containerId: Int, newsDetail:Result) {
            val fragment = NewsDetailFragment()
            val bundle = Bundle().apply {
                putParcelable(Constants.NEWS_DETAIL, newsDetail)
            }
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .addToBackStack(TAG)
                .add(containerId, fragment)
                .commit()
        }
    }
}