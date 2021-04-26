package com.example.passmanager.roomaddsite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.passmanager.roomlogin.UserInfo
import com.example.passwordmanager.roomaddsite.AddSiteInfo

@Dao
interface MysiteDao {

    @Insert
    fun addSite(user: AddSiteInfo)

    @Query("Select * from addsite_table")
    fun getSites():List<AddSiteInfo>

    @Query("Delete from addsite_table")
    fun deleteSite()

    @Query("update addsite_table set SiteName=:UpdatedSname,Url=:UpdatedUrl,Folder=:UpdatedFolder,UserName=:UpdatedUname,SitePwd=:UpdatedPwd,Notes=:UpdatedNotes  where SiteName=:OldSitetName")
    fun update(UpdatedSname:String,UpdatedUrl:String,UpdatedFolder:String,UpdatedUname:String,UpdatedPwd:String,UpdatedNotes:String,OldSitetName:String)

    @Query("SELECT * FROM addsite_table WHERE Folder = :folder and Mobnum = :mob")
    fun selectfolder(folder : String, mob : String):List<AddSiteInfo>

    @Query("SELECT * FROM addsite_table WHERE Folder = :folder")
    fun selectfolderonly(folder : String):List<AddSiteInfo>

    @Query("SELECT * FROM addsite_table WHERE SiteName LIKE :site and Folder = :fold and Mobnum = :mob ")
    fun searchSite(site : String, fold : String, mob : String):List<AddSiteInfo>

    //@Query("update user_table set ContactName=:UpdatedName,ContactNumber=:UpdatedNumber  where ContactName=:OldContactName")
    //fun update(UpdatedName:String,UpdatedNumber:String,OldContactName:String)

    //@Query("Delete from addsite_table where MobileNum = :name")
    //fun deleteuser(name: String)

    //@Query("SELECT EXISTS(SELECT * FROM addsite_table WHERE MobileNum = :name)")
    //fun isFavouriteAdded(name : String) : Boolean

    //@Query("SELECT EXISTS(SELECT Mpin FROM addsite_table WHERE MobileNum = :name)")
    //@Query("SELECT Mpin FROM addsite_table WHERE MobileNum = :name")
    //fun isPWAdded(name : String) : String

}