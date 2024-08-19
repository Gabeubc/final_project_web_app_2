package com.webapp2.communication.route.utils

import com.webapp2.communication.dto.MailDto
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateEmail {
    fun createMimeMessage(mailDto: MailDto): MimeMessage {
        val properties = Properties()
        val session = Session.getDefaultInstance(properties, null)
        return MimeMessage(session).apply {
            mailDto.receivers.forEach { addRecipient(MimeMessage.RecipientType.TO, InternetAddress(it)) }
            subject = mailDto.mailObject
            setText(mailDto.body)
        }
    }
}