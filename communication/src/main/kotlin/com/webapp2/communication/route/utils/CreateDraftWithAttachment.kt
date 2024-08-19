package com.webapp2.communication.route.utils

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.Draft
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import com.webapp2.communication.dto.MailDto
import jakarta.activation.DataHandler
import jakarta.activation.FileDataSource
import jakarta.mail.Message
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.util.*


@Component
class CreateDraftWithAttachment(
    @Autowired
    private var createMessage: CreateMessage
) {
    fun createDraftMessageWithAttachment(
        mailDto: MailDto
    ): Draft? {
        val credentials = GoogleCredentials.getApplicationDefault()
            .createScoped(GmailScopes.MAIL_GOOGLE_COM)
        val requestInitializer: HttpRequestInitializer = HttpCredentialsAdapter(credentials)

        // Create the gmail API client
        val service = Gmail.Builder(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            requestInitializer
        )
            .setApplicationName("Gmail samples")
            .build()

        // Create the email content
        val messageSubject = "Test message"
        val bodyText = "lorem ipsum."

        // Encode as MIME message
        val props = Properties()
        val session = Session.getDefaultInstance(props, null)
        val email = MimeMessage(session)
        email
            .setFrom(InternetAddress(mailDto.sender))
        email
            .addRecipients(
                Message.RecipientType.TO,
                mailDto.receivers
                    .map { InternetAddress(it) }
                    .toTypedArray()
            )
        email
            .setSubject(messageSubject)

        var mimeBodyPart = MimeBodyPart()
        mimeBodyPart
            .setContent(bodyText, "text/plain")
        val multipart = MimeMultipart()
        multipart
            .addBodyPart(mimeBodyPart)
        mimeBodyPart = MimeBodyPart()
        //val source = FileDataSource(file)
        //mimeBodyPart
        //    .dataHandler = DataHandler(source)
        //mimeBodyPart
        //    .fileName = file.name
        //multipart
        //    .addBodyPart(mimeBodyPart)
        email
            .setContent(multipart)

        // Encode and wrap the MIME message into a gmail message
        val message = createMessage.createMessageWithEmail(email)
        try{
            var draft = Draft()
            draft.setMessage(message)
            draft = service.users().drafts().create("me", draft).execute()
            println("Draft id: " + draft.id)
            println(draft.toPrettyString())
            return draft
        }catch(e: GoogleJsonResponseException) {

            // TODO(developer) - handle error appropriately
            val error = e.details
            if (error.code == 403) {
                System.err.println("Unable to create draft: " + e.details)
            } else {
                throw e
            }
        }
        return null
    }
}