package com.webapp2.crm.repository

import com.webapp2.crm.entity.contact.Operator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository : JpaRepository<Operator, Long>