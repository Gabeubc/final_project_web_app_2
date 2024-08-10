package com.webapp2.communication.repository.management

import com.webapp2.communication.entity.contact.professional.Professional
import org.springframework.data.jpa.repository.JpaRepository

interface ProfessionalRepository : JpaRepository<Professional, Long>