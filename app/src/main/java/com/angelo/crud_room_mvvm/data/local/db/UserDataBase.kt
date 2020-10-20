package com.angelo.crud_room_mvvm.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.angelo.crud_room_mvvm.data.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDataBase():RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        //Generar singleton
        private var INSTANCE: UserDataBase ?= null
        private const val DBNAME = "crud.db"

        fun getInstance(ctx: Context): UserDataBase{
            val tempInstance =  INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    UserDataBase::class.java,
                    DBNAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }


}