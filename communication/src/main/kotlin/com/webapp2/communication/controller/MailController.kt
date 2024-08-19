package com.webapp2.communication.controller

import com.webapp2.communication.dto.MailDto
import org.apache.camel.CamelContext
import org.apache.camel.ProducerTemplate
import org.apache.camel.builder.ExchangeBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/API/emails"])
class MailController@Autowired constructor(
    private val camelContext: CamelContext,
    private val producerTemplate: ProducerTemplate
) {
    @PostMapping(value = [""])
    fun sendMail(
        @RequestBody mailDto: MailDto
    ): Boolean {
        val exchange = ExchangeBuilder.anExchange(camelContext).withBody(mailDto).build()
        producerTemplate.send("direct:sendEmail", exchange)
        return true
    }
}