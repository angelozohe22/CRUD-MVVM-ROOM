package com.angelo.crud_room_mvvm.data.local.source

import androidx.lifecycle.LiveData
import com.angelo.crud_room_mvvm.data.model.UserEntity

interface LocalDataSource {

    fun getAllUser(): LiveData<List<UserEntity>>
    suspend fun getUserById(idUser: Int): UserEntity
    suspend fun insertUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    suspend fun deleteAllUsers()
}