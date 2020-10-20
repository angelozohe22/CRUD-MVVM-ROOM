package com.angelo.crud_room_mvvm.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_user")
data class UserEntity(
    @ColumnInfo(name = "id_user")
    @PrimaryKey(autoGenerate = true) var idUser     : Int,
    @ColumnInfo(name = "name_user")  val username   : String,
    @ColumnInfo(name = "email_user") val email      : String
): Parcelable