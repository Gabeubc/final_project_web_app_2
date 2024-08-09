package com.webapp2.document_store.entity

import com.webapp2.document_store.dto.utils.EntityBaseId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity(name="WA_2_DOCUMENT")
data class Document(
    var content: ByteArray?,
    @OneToOne
    @JoinColumn(
        name="document_metadata_id"
    )
    var metadata: DocumentMetadata?
): EntityBaseId<Long>()