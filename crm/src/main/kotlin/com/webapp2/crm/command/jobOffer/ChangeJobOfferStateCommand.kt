package com.webapp2.crm.command.jobOffer

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.jobOffer.JobOfferStateDto
import com.webapp2.crm.service.jobOffer.JobOfferService
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChangeJobOfferStateCommand(
    @Autowired
    private val jobOfferService: JobOfferService
) : Command<Boolean> {
    private var jobOfferId: Long = EMPTY_ID
    private var state: JobOfferStateDto? = null
    fun setChangeJobOfferStateCommand(jobOfferId: Long, state: JobOfferStateDto){
        this.jobOfferId = jobOfferId
        this.state = state
    }
    override fun execute(): Boolean {
        return when(state == null){
            true -> false
            else -> jobOfferService.changeJobOfferState(jobOfferId, state!!)
        }
    }
}