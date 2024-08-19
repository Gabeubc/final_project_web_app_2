package com.webapp2.communication.route.utils

import com.google.api.services.gmail.model.Message
import jakarta.mail.internet.MimeMessage
import org.springframework.stereotype.Component
import java.io.ByteArrayOutputStream
import java.util.*

@Component
class CreateMessage {
    fun createMessageWithEmail(mimeMessage: MimeMessage): Message {
        val byteArrayOutputStream = ByteArrayOutputStream()
        mimeMessage.writeTo(byteArrayOutputStream)
        return Message().setRaw(Base64.getUrlEncoder().encodeToString(byteArrayOutputStream.toByteArray()))
    }
}