package com.source.truecallerapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.source.truecallerapp.R
import com.source.truecallerapp.network.NetworkHelper
import com.source.truecallerapp.utils.Constants.TITLE
import com.source.truecallerapp.utils.Display.showSnack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = TITLE

        networkHelper = NetworkHelper(this)

        if (networkHelper.isNetworkConnected()) {
            loadData()
        } else {
            showSnack(main, R.string.network_connection_error)
        }
    }

    private fun loadData() {

        load_data.setOnClickListener {

            viewModel = ViewModelProviders.of(this, MainViewModelFactory()).get(MainViewModel::class.java)

            /*viewModel.tenthChar.observe(this, Observer {
                it?.let { char_data_view.text = it }
            })

            viewModel.everyTenthChar.observe(this, Observer {
                it?.let { everytenthchar_data_view.text = it }
            })

            viewModel.wordsCount.observe(this, Observer {
                it?.let { wordcount_data_view.text = it }
            })*/
        }

    }
}
