package com.webapp2.communication.repository.contact

import com.webapp2.communication.entity.contact.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository : JpaRepository<Contact, Long?>