package com.example.dz1

import androidx.lifecycle.*

class VM(state: SavedStateHandle): ViewModel() {
    companion object{
        const val KEY_NUMBER = "number"
        const val stockNumber = 5
    }
    private val savedStateHandle = state

    private val _number: MutableLiveData<Int> = savedStateHandle.getLiveData(KEY_NUMBER)
    val number: LiveData<Int> = _number

    private val _numberList = MutableLiveData<List<Int>>()
    val numberList: LiveData<List<Int>> = _numberList

    init {
        createList(number.value?: stockNumber)
    }

    private fun saveCurrentNumber(newNumber: Int){
        savedStateHandle[KEY_NUMBER] = newNumber
    }

    fun incrementNumber(){
        saveCurrentNumber((number.value ?: stockNumber) + 1)
        createList(number.value!!)
    }

    private fun createList(number: Int){
        val res = mutableListOf<Int>()
        for (i in 0..number) {
            res.add(i)
        }
        _numberList.postValue(res)
    }

}