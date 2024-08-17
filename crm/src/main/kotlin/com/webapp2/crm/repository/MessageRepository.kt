package com.webapp2.crm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MesssageRepository : JpaRepository<Message, Long>