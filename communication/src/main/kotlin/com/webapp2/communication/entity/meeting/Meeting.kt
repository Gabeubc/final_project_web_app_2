package com.webapp2.communication.entity.meeting

import com.webapp2.communication.dto.jobOffer.JobOffer
import com.webapp2.communication.entity.message.Message
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.Entity
import java.time.LocalDateTime

@Entity(name = "WA2_MEETING")
data class Meeting(
    var jobOffer: JobOffer,
    var message: Message,
    var link: String,
    var comment: String,
    var time: LocalDateTime
):EntityBaseId<Long>()