package com.webapp2.crm.service

import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.repository.ProfessionalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfessionalServiceImpl(
    @Autowired
    private val professionalRepository: ProfessionalRepository
): ProfessionalService {
    @Transactional
    override fun getProfessionals(): List<ContactDto> {
        return professionalRepository.findAll()
            .mapNotNull { it.contact!!.toDto() }
    }
}