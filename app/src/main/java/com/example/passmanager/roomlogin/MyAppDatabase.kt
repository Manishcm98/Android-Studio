package com.example.passmanager.roomlogin

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.passmanager.roomaddsite.MysiteDao
import com.example.passwordmanager.roomaddsite.AddSiteInfo

//@Database(entities = [UserInfo::class], version = 1)
@Database(entities = [UserInfo::class, AddSiteInfo::class], version = 2)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
    abstract fun mysiteDao(): MysiteDao
}