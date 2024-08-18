package com.webapp2.crm.repository

import com.webapp2.crm.entity.contact.Professional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfessionalRepository : JpaRepository<Professional, Long>