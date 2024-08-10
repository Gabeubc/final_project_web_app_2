package com.webapp2.communication.entity.contact

import com.webapp2.communication.dto.contact.ContactInformationDto
import com.webapp2.communication.dto.jobOffer.JobOffer
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity(name="WA2_CONTACT_INFORMATION")
data class ContactInformation (
    @ManyToMany(
        mappedBy = "contactInformation",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    var contactDto: MutableSet<Contact>,
    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOfferForCustomer: MutableSet<JobOffer>,
    @OneToMany(
        mappedBy = "recruiters",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var jobOfferHandledByRecruiter: MutableSet<JobOffer>,
    var email: String,
    var address: String,
    var phoneNumber: String
): EntityBaseId<Long>(){
    fun toDto(): ContactInformationDto {
        TODO()
    }
}