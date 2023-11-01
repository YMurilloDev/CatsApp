package com.example.catsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapp.domain.Cat
import com.example.catsapp.domain.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repository: CatsRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Cat>>()
    val liveData: LiveData<List<Cat>> = mutableLiveData

    fun loadCats() {
        viewModelScope.launch(Dispatchers.IO) {
            val cats = repository.fetchCats()
            launch(Dispatchers.Main) {
                mutableLiveData.postValue(cats)
            }
        }
    }
}
