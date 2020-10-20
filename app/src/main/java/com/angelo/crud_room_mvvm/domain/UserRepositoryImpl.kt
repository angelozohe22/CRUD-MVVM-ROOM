package com.angelo.crud_room_mvvm.domain

import androidx.lifecycle.LiveData
import com.angelo.crud_room_mvvm.data.local.source.LocalDataSource
import com.angelo.crud_room_mvvm.data.model.UserEntity

class UserRepositoryImpl(private val localDataSource: LocalDataSource):UserRepository {

    override fun getAllUser(): LiveData<List<UserEntity>> {
        return localDataSource.getAllUser()
    }

    override suspend fun getUserById(idUser: Int): UserEntity {
        return localDataSource.getUserById(idUser)
    }

    override suspend fun insertUser(user: UserEntity) {
        localDataSource.insertUser(user)
    }

    override suspend fun updateUser(user: UserEntity) {
        localDataSource.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        localDataSource.deleteUser(user)
    }

    override suspend fun deleteAllUsers() {
        localDataSource.deleteAllUsers()
    }
}