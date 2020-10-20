package com.angelo.crud_room_mvvm.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.angelo.crud_room_mvvm.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM table_user")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM table_user")
    fun getAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM table_user WHERE id_user = :idUser")
    suspend fun getUserById(idUser: Int): UserEntity


}