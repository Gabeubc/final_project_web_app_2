package com.webapp2.crm.entity.contact

import com.webapp2.crm.entity.jobOffer.JobOffer
import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.*

@Entity(name = "WA2_PROFESSIONAL")
data class Professional(
    @OneToOne(
        mappedBy = "professional",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var contact: Contact? = null,
    @ManyToMany(
        mappedBy = "professionals",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffers: MutableSet<JobOffer>? = null,
    @OneToMany(
        mappedBy = "professional",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var professionalState: MutableSet<ProfessionalState>? = null,
    @OneToMany(
        mappedBy = "assignedTo",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOffer: MutableSet<JobOffer>? = null
) : EntityBaseId<Long>() {
    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Professional) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}