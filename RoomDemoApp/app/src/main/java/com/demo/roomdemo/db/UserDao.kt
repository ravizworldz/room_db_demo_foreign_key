package com.demo.roomdemo.db

import androidx.room.*

@Dao
interface UserDao {


    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getAllUserInfo(): List<UserEntity>?



    @Insert
    fun insertUser(user: UserEntity?): Long

    @Delete
    fun deleteUser(user: UserEntity?)

    @Update
    fun updateUser(user: UserEntity?)


    //for SECOND TABLE INSERT
    @Insert
    fun insertAddress(user: Address?)

    //FOR RETRIEVE DATA FROM BOTH TABLE
    @Query("SELECT * FROM userinfo inner join Address  on userinfo.id = Address.user_id ")
    fun getAllUserInfo2(): List<UserWithAddress>?

}