package com.webapp2.communication.route

import com.webapp2.communication.dto.MailDto
import com.webapp2.communication.route.utils.CreateEmail
import com.webapp2.communication.route.utils.CreateMessage
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.component.google.mail.GoogleMailEndpoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmailRoute @Autowired constructor(
    private val createEmail: CreateEmail,
    private val createMessage: CreateMessage
): RouteBuilder() {

    override fun configure() {
        from("direct:sendEmail")
            .routeId("sendEmailRoute")
            .process { exchange ->
                val mailDto = exchange.getIn().getBody(MailDto::class.java)
                val mimeMessage = createEmail.createMimeMessage(mailDto)
                val gmailMessage = createMessage.createMessageWithEmail(mimeMessage)

                exchange.getIn().body = gmailMessage
            }
            .to("google-mail://messages/send?inBody=content&userId=me")
            .log("Email sent successfully")
    }
}