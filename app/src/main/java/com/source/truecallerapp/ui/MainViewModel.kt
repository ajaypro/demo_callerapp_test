package com.source.truecallerapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.source.truecallerapp.data.DataRepository
import com.source.truecallerapp.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val dataRepository: DataRepository): ViewModel() {


    private val tenthCharData: MutableLiveData<String> = MutableLiveData()
    private val everyTenthCharData: MutableLiveData<String> = MutableLiveData()
    private val everyWordCountData: MutableLiveData<String> = MutableLiveData()

    private fun tenthChar(): MutableLiveData<String> {
        viewModelScope.launch {
            when (val result =  //async(Dispatchers.IO) { dataRepository.fetchTenthCharacter() }.await())
                withContext(Dispatchers.IO) { dataRepository.fetchTenthCharacter() }) {
                is Result.Success -> {
                    tenthCharData.value = result.data
                }
            }
        }
        return tenthCharData
    }


    private fun everyTenthChar(): MutableLiveData<String> {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { dataRepository.fetchEveryTenthcharacter() }) {
                is Result.Success -> {
                    everyTenthCharData.value = result.data
                }
            }
        }
        return everyTenthCharData
    }

    private fun everyWordCount(): MutableLiveData<String> {
        viewModelScope.launch {
            when (val result =
                withContext(Dispatchers.IO) { dataRepository.fetchWordCount() }) {
                is Result.Success -> {
                    everyWordCountData.value = result.data
                }
            }
        }
        return everyWordCountData
    }

    val tenthChar: LiveData<String> = tenthChar()

    val everyTenthChar: LiveData<String> = everyTenthChar()

    val wordsCount: LiveData<String> = everyWordCount()


}

