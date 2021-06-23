package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.OnBoard
import com.example.myapplication.utils.data
import com.example.myapplication.utils.states

class OnBoardViewModel : ViewModel() {

    private val _state = MutableLiveData<OnBoard>()
    val state: LiveData<OnBoard>
        get() = _state

    private val _currentPos = MutableLiveData<Int>()
    val currentPos: LiveData<Int>
        get() = _currentPos

    private val _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    init {
        _state.value = states[0]
        _currentPos.value = 0
        _navigate.value = false
    }

    fun changePage(pos: Int){
        if (pos == states.size)
            navigateToDestination()
        else
            _currentPos.value = pos
    }

    fun updateState(pos: Int = _currentPos.value!!){
        _state.value = states[pos]
    }

    fun navigateToDestination(){
        _navigate.value = true
    }

    fun navigateToDestinationComplete(){
        _navigate.value = false
    }
}