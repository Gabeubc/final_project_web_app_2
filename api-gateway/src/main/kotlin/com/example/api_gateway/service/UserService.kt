package com.example.api_gateway.service

import com.example.api_gateway.model.User

interface UserServcice {
    fun createUser(user: User): Boolean
}