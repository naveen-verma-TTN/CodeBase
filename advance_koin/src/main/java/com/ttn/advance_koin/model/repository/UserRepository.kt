package com.ttn.advance_koin.model.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ttn.advance_koin.model.api.UserApi
import com.ttn.advance_koin.model.dao.UserDao

class UserRepository(private val userApi: UserApi, private val userDao: UserDao) {

    val data = userDao.findAll()

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val users = userApi.getAllAsync().await()
            userDao.add(users)
        }
    }
}