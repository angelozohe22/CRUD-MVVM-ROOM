package com.angelo.crud_room_mvvm.ui.viewmodels.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelo.crud_room_mvvm.data.model.UserEntity
import com.angelo.crud_room_mvvm.domain.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: UserRepository):ViewModel() {

    fun deleteUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

}