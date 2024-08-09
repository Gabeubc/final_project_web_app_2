package com.webapp2.document_store.entity

import com.webapp2.document_store.dto.DocumentDto
import com.webapp2.document_store.dto.utils.EntityBaseId
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

@Entity(name="WA_2_DOCUMENT_METADATA")
data class DocumentMetadata(
    @OneToOne(
        mappedBy = "metadata",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY
    )
    var document: Document,
    var name: String,
    var size: Int,
    var timeStamp: LocalDateTime,
    var contentType: String
): EntityBaseId<Long>(){
    fun toDto(): DocumentDto = DocumentDto(
            null,
            name,
            size,
            timeStamp,
            contentType
        )
    fun toDtoWithContent()= DocumentDto(
        document.content,
        name,
        size,
        timeStamp,
        contentType
    )

}