package com.webapp2.crm.repository

import com.webapp2.crm.entity.message.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long>