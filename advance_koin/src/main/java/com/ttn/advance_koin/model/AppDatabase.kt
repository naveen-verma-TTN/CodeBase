package com.ttn.advance_koin.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ttn.advance_koin.model.dao.UserDao
import com.ttn.advance_koin.model.entity.GithubUser

@Database(entities = [GithubUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}