package com.webapp2.document_store.dto

import com.webapp2.document_store.dto.utils.EntityBaseId
import com.webapp2.document_store.entity.Document
import com.webapp2.document_store.entity.DocumentMetadata
import java.time.LocalDateTime

data class DocumentDto(
    var content: ByteArray?=null,
    var name: String?=null,
    var size: Int?=null,
    var timeStamp: LocalDateTime?=null,
    var contentType: String?=null
): EntityBaseId<Long>(){
    fun toEntity(): DocumentMetadata{
        val entity = DocumentMetadata(
            Document(
                content,
                null
            ),
            name!!,
            size!!,
            timeStamp!!,
            contentType!!
        )
        entity.document.metadata = entity
        return entity
    }
}