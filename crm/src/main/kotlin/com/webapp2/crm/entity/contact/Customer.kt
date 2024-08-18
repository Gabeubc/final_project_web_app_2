package com.webapp2.crm.entity.contact

import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.message.Interview
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.*
import java.io.Serializable

@Entity(name="WA2_CUSTOMER")
data class Customer (
    @OneToOne(
        mappedBy ="customer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var contact: Contact? = null,
    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffers: MutableSet<JobOffer>? = null,
    @OneToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var noteOnCustomer: NoteOnCustomer? = null,
    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var interview: MutableSet<Interview> ? = null
): EntityBaseId<Long>(), Serializable{
    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Customer) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}