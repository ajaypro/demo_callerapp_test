package com.source.truecallerapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.source.truecallerapp.R
import com.source.truecallerapp.databinding.ActivityMainBinding
import com.source.truecallerapp.network.NetworkHelper
import com.source.truecallerapp.showSnack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        networkHelper = NetworkHelper(this)

        if (networkHelper.isNetworkConnected()) {
            load_data.setOnClickListener {

                viewModel = ViewModelProviders.of(this, MainViewModelFactory())
                    .get(MainViewModel::class.java)

                // binding viewmodel and assigning lifecycleowner for observing livedata
                binding.apply {
                    mainViewModel = viewModel
                    lifecycleOwner = this@MainActivity  
                }
            }
        } else {
            showSnack(main, R.string.network_connection_error)
        }
    }
}
