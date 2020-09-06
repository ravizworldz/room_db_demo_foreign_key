package com.demo.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")//==========TABLE - 1 WITH PRIMARY KEY
data class UserEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0 ,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String?
)

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE)
))
data class Address (// ==========TABLE - 2 with FOREIGN KEY
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "add_id") val add_id : Int = 0,
    @ColumnInfo(name = "user_id") val id: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "zip") val zip: String?
)