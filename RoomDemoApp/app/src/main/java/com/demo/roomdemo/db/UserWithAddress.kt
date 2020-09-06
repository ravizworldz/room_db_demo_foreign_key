package com.demo.roomdemo.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Relation

//MODEL CLASS which hold both table data......
data class UserWithAddress(var id: String, val name: String,val email: String,val phone: String?,
                           val add_id : Int = 0, val street: String,val city: String,val state: String?,val zip: String?
)