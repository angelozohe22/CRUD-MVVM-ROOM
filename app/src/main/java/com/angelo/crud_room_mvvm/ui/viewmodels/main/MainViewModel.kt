package com.angelo.crud_room_mvvm.ui.viewmodels.main

import android.widget.Toast
import androidx.lifecycle.*
import com.angelo.crud_room_mvvm.data.model.UserEntity
import com.angelo.crud_room_mvvm.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: UserRepository): ViewModel() {

    val fetchUserList = repository.getAllUser()

    fun insertUser(user: UserEntity){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.insertUser(user)
            }
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAllUsers()
        }
    }







}