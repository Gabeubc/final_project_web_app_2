package com.webapp2.crm.entity.contact

import com.webapp2.crm.entity.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne

@Entity(name = "WA2_NOTE_ON_CUSTOMER")
data class NoteOnCustomer(
    @OneToOne(
        mappedBy = "noteOnCustomer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var customer: Customer? = null,
    @OneToOne(
        mappedBy = "noteOnCustomer",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var operator: Operator? = null,
    var note: String? = null
) : EntityBaseId<Long>(){
    override fun toString(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NoteOnCustomer) return false
        if (!super.equals(other)) return false

        return id == other.id
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}