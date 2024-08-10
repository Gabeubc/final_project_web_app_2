package com.webapp2.communication.repository.message

import com.webapp2.communication.entity.message.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, Long?>