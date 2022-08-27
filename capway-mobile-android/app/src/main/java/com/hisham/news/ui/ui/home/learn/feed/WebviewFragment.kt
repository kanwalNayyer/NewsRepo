package com.hisham.news.ui.ui.home.learn.feed

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity
import com.hisham.news.BR
import com.hisham.news.base.BaseFragment
import com.hisham.news.databinding.FragmentWebviewBinding
import com.hisham.news.utils.Constants
import kotlinx.android.synthetic.main.fragment_webview.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WebviewFragment : BaseFragment<FragmentWebviewBinding, NewsViewModel>() {

    override fun getLayoutId(): Int = com.hisham.news.R.layout.fragment_webview
    override fun getViewModel(): NewsViewModel = newsViewModel
    private val newsViewModel: NewsViewModel by viewModel()

    lateinit var url: String

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        initView()
    }
    private fun getIntentData()
    {
        url = arguments?.getString(Constants.NEWS_URL)!!
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
            bodyTextFeedDetail.settings.javaScriptEnabled = true
            bodyTextFeedDetail.settings.loadWithOverviewMode = true
            bodyTextFeedDetail.settings.domStorageEnabled = true
            bodyTextFeedDetail.settings.setMediaPlaybackRequiresUserGesture(false);
        bodyTextFeedDetail.loadUrl(url)

            bodyTextFeedDetail.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }
                @SuppressLint("WebViewClientOnReceivedSslError")
                override
                fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    var message = "SSL Certificate error."
                    when (error!!.primaryError) {
                        SslError.SSL_UNTRUSTED -> message =
                            "The certificate authority is not trusted."
                        SslError.SSL_EXPIRED -> message = "The certificate has expired."
                        SslError.SSL_IDMISMATCH -> message = "The certificate Hostname mismatch."
                        SslError.SSL_NOTYETVALID -> message = "The certificate is not yet valid."
                    }
                    message += " Do you want to continue anyway?"

                    builder.setTitle("SSL Certificate Error")
                    builder.setMessage(message)
                    builder.setPositiveButton("continue",
                        DialogInterface.OnClickListener { dialog, which -> handler!!.proceed() })
                    builder.setNegativeButton("cancel",
                        DialogInterface.OnClickListener { dialog, which -> handler!!.cancel() })
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }
    }


    private fun initView() {
            back.setOnClickListener {
              parentFragmentManager.popBackStack()
            }
    }

    companion object {
        val TAG = WebviewFragment::class.java.simpleName

        fun start(activity: FragmentActivity, containerId: Int, url:String) {
            val fragment = WebviewFragment()
            val bundle = Bundle().apply {
                putString(Constants.NEWS_URL, url)
            }
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }
}