package com.webapp2.crm.entity.jobOffer

import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity(name= "WA2_JOB_OFFER_STATE")
data class JobOfferState(
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffer: JobOffer?= null,
    var state: String?= "",
    var date: LocalDateTime = now()
): EntityBaseId<Long>(){
    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JobOfferState) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}
