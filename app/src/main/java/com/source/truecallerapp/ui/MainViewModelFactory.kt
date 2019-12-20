package com.source.truecallerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.source.truecallerapp.data.DataRepository
import com.source.truecallerapp.data.DataSource

class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(DataRepository(DataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
