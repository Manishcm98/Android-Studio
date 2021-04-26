package com.example.passmanager.roomlogin

import androidx.room.*
import java.math.BigInteger

@Dao
interface MyDao {

    @Insert
    fun addUser(user: UserInfo)

    @Query("Select * from signup_table")
    fun getUser():List<UserInfo>

    @Query("Delete from signup_table")
    fun delete()

    @Query("Delete from signup_table where MobileNum = :name")
    fun deleteuser(name: String)

    @Query("SELECT EXISTS(SELECT * FROM signup_table WHERE MobileNum = :name)")
    fun isFavouriteAdded(name : String) : Boolean

    //@Query("SELECT EXISTS(SELECT Mpin FROM signup_table WHERE MobileNum = :name)")
    @Query("SELECT Mpin FROM signup_table WHERE MobileNum = :name")
    fun isPWAdded(name : String) : String

}