package com.example.passmanager.roomlogin

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigInteger

@Parcelize
@Entity(tableName = "signup_table")
data class UserInfo (
    @PrimaryKey
    var MobileNum : String,
    var Mpin : String
):Parcelable