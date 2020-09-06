package com.demo.roomdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.demo.roomdemo.db.Address
import com.demo.roomdemo.db.RoomAppDb
import com.demo.roomdemo.db.UserEntity
import com.demo.roomdemo.db.UserWithAddress

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    lateinit var allUsers : MutableLiveData<List<UserEntity>>

    init{
        allUsers = MutableLiveData()
        getAllUsers()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUsers() {
        val userDao = RoomAppDb.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()
        val list2 = userDao?.getAllUserInfo2()

        Log.e("=======", "=======list2 : " +list2)

        allUsers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity): Long?{//RETURN PRIMARY KEY FROM USER TABLE
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        val id = userDao?.insertUser(entity)
        getAllUsers()
        return id
    }

    //INSERT DATA IN ADDRESS TABLE
    fun insertAddress(address: Address){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.insertAddress(address)

        //RETRIEVE DATA FROM USER AND ADDRESS TABLE.....
        val list2  = userDao?.getAllUserInfo2()
        Log.e("=======", "=======list2 : " +list2)
        Log.e("=======", "=======list2 size : " +list2?.size)
        Log.e("=======", "=======list2 size : " +list2?.toString())

    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }
}