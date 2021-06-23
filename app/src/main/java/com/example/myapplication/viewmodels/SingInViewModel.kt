package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingInViewModel: ViewModel() {

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate
    init {
        _navigate.value = false
    }

    fun navigateToDestination(){
        _navigate.value = true
    }

    fun navigationDone(){
        _navigate.value = false
    }
}