package com.webapp2.crm.entity.message

import com.webapp2.crm.entity.contact.Customer
import com.webapp2.crm.entity.contact.Operator
import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.jobOffer.Proposal
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity(name = "WA2_INTERVIEW")
data class Interview(
    var startTime: LocalDateTime? = now(),
    var endTime: LocalDateTime? = null,
    var link: String? = "",
    var note: String? = "",
    var type: String? = "",
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var customer: Customer? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffer: JobOffer? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var operator: Operator? = null,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var message: Message,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(nullable = true)
    var proposal: Proposal
) : EntityBaseId<Long>(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Interview) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}