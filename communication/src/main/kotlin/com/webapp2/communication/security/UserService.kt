package com.example.api_gateway.service

import com.example.api_gateway.model.User

interface UserService {
    fun createUser(user: User): String
}