package com.webapp2.communication.entity.contact.professional

import com.webapp2.communication.entity.contact.Contact
import com.webapp2.communication.dto.jobOffer.JobOffer
import com.webapp2.communication.dto.jobOffer.Skill
import com.webapp2.communication.entity.utils.EntityBaseId
import jakarta.persistence.Entity
import jakarta.persistence.ManyToMany

@Entity(name = "WA2_PROFESSIONAL")
data class Professional(
    @ManyToMany
    var jobOffers: MutableSet<JobOffer>,
    var contact: Contact,
    var skills: MutableSet<Skill>,
    var location: String,
    var state: String,
    var selected: Boolean,
    var accepted: Boolean
) : EntityBaseId<Long>()