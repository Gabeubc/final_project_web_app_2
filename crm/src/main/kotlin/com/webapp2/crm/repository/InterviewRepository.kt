package com.webapp2.crm.repository

import com.webapp2.crm.entity.message.Interview
import org.springframework.data.jpa.repository.JpaRepository

interface InterviewRepository : JpaRepository<Interview, Long>