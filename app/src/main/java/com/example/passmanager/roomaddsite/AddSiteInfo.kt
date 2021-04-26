package com.example.passwordmanager.roomaddsite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigInteger

@Parcelize
@Entity(tableName = "addsite_table")
data class AddSiteInfo (
        @PrimaryKey
        var SiteName : String,
        var Url : String,
        var Folder : String,
        var UserName : String,
        var SitePwd : String,
        var Notes : String,
        var Mobnum : String
):Parcelable