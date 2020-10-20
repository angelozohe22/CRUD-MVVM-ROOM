package com.angelo.crud_room_mvvm.data.local.source

import androidx.lifecycle.LiveData
import com.angelo.crud_room_mvvm.data.local.db.UserDataBase
import com.angelo.crud_room_mvvm.data.model.UserEntity

class LocalDataSourceImpl(private val userDataBase: UserDataBase):LocalDataSource {

    override fun getAllUser(): LiveData<List<UserEntity>> {
        return userDataBase.userDao().getAllUsers()
    }

    override suspend fun getUserById(idUser: Int): UserEntity {
        return userDataBase.userDao().getUserById(idUser)
    }

    override suspend fun insertUser(user: UserEntity) {
        userDataBase.userDao().insertUser(user)
    }

    override suspend fun updateUser(user: UserEntity) {
        userDataBase.userDao().updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        userDataBase.userDao().deleteUser(user)
    }

    override suspend fun deleteAllUsers() {
        userDataBase.userDao().deleteAllUsers()
    }
}