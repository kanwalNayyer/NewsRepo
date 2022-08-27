package com.hisham.news.ui.ui.home

import android.os.Bundle
import com.hisham.news.BR
import com.hisham.news.R
import com.hisham.news.base.BaseActivity
import com.hisham.news.databinding.ActivityNavigationBinding
import com.hisham.news.ui.ui.home.learn.feed.NewsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
class NavigationActivity : BaseActivity<ActivityNavigationBinding, NavigationViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_navigation
    override fun getViewModel(): NavigationViewModel = navigationViewModel
    private val navigationViewModel: NavigationViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsFragment.start(this, R.id.main_screen_container)
    }

}