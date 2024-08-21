package com.webapp2.crm.service

import com.webapp2.crm.dto.jobOffer.JobOfferDto
import com.webapp2.crm.dto.jobOffer.JobOfferStateDto
import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.jobOffer.JobOfferState
import com.webapp2.crm.entity.skill.Skill
import com.webapp2.crm.repository.*
import com.webapp2.crm.utils.GeneralConstant
import com.webapp2.crm.utils.GeneralConstant.Companion.CREATED
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JobOfferServiceImpl(
    @Autowired
    private val jobOfferRepository: JobOfferRepository,
    @Autowired
    private val customerRepository: CustomerRepository,
    @Autowired
    private val professionalRepository: ProfessionalRepository,
    @Autowired
    private val operatorRepository: OperatorRepository,
    @Autowired
    private val contactRepository: ContactRepository
) : JobOfferService {
    @Transactional
    override fun getJobOffersByStateAndCustomerId(customerId: Long, state: String): List<JobOfferDto> {
        val customer = customerRepository.findById(customerId).orElse(null)
        val entities = jobOfferRepository.findByCustomer(customer)
        return entities
            .map { it.toDto() }
            .filter { it.state!!.value == state }
    }

    @Transactional
    override fun getJobOfferByStateAndProfessional(professionalId: Long, state: String): List<JobOfferDto> {
        val professional = professionalRepository.findById(professionalId).orElse(null)
        val entities = jobOfferRepository.findByProfessionals(mutableSetOf(professional))
        return entities
            .map { it.toDto() }
            .filter { it.state!!.value == state }
    }

    @Transactional
    override fun getJobOfferByState(state: String): List<JobOfferDto> {
        val entities = jobOfferRepository.findAll()
        return entities
            .map { it.toDto() }
            .filter { it.state!!.value == state }
    }

    @Transactional
    override fun getJobOffers(): List<JobOfferDto> {
        return jobOfferRepository.findAll().map { it.toDto() }
    }

    @Transactional
    override fun changeJobOfferState(jobOfferId: Long, jobOfferStateDto: JobOfferStateDto): Boolean {
        try {
            val entity = jobOfferRepository.findById(jobOfferId).orElse(null)
            val state = JobOfferState()
            state.state = jobOfferStateDto.value
            state.jobOffer = entity
            entity.jobOfferStates!!.add(state)
            jobOfferRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    @Transactional
    override fun getJobOfferValue(jobOfferId: Long): Double {
        return jobOfferRepository.findById(jobOfferId).orElse(null).value
    }

    @Transactional
    override fun createJobOffer(jobOfferDto: JobOfferDto): Boolean {
        try {
            val entity = JobOffer()
            entity.description = jobOfferDto.description
            val skills = jobOfferDto
                .skills!!
                .map {
                    val skillEntity = Skill()
                    skillEntity.name = it.name
                    skillEntity.jobOffers.add(entity.copy())
                    skillEntity
                }.toMutableSet()
            entity.skills!!.addAll(skills.map { it.copy() })
            val customer = contactRepository
                .findById(jobOfferDto.customer!!.id!!)
                .orElse(null)
                .customer
            entity.customer = customer
            if (jobOfferDto.operator != null && jobOfferDto!!.operator!!.id != null) {
                val operator = contactRepository
                    .findById(jobOfferDto.operator!!.id!!)
                    .orElse(null)
                    .operator
                entity.operator = operator
            }
            val state = JobOfferState()
            state.state = CREATED
            state.jobOffer = entity
            entity.jobOfferStates!!.add(state.copy())
            entity.publicationTime = jobOfferDto.publicationTime
            entity.endTime = jobOfferDto.endTime
            jobOfferRepository.save(entity)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}