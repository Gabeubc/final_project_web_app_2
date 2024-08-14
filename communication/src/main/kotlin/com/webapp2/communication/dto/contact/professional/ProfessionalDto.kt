package com.webapp2.communication.entity.contact.professional

import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.entity.jobOffer.JobOffer
import com.webapp2.communication.entity.jobOffer.Skill
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_PROFESSIONAL")
data class Professional(
    @ManyToMany
    var jobOffers: MutableSet<JobOffer> = mutableSetOf(),
    var contact: Contact,
    @ManyToMany
    var skills: MutableSet<Skill> = mutableSetOf(),
    var location: String,
    var state: String,
    var selected: Boolean,
    var accepted: Boolean
) : EntityBaseId<Long>(){
}
