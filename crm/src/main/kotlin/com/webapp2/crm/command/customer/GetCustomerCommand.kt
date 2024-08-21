package com.webapp2.crm.command.customer

import com.webapp2.crm.command.Command
import com.webapp2.crm.dto.contact.ContactDto
import com.webapp2.crm.service.CustomerService
import com.webapp2.crm.utils.GeneralConstant
import com.webapp2.crm.utils.GeneralConstant.Companion.EMPTY_ID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetCustomerCommand(
    @Autowired
    private val customerService: CustomerService
) : Command<List<ContactDto>> {
    private var customerId: Long = EMPTY_ID
    private var value: String = GeneralConstant.EMPTY_STRING

    fun setGetCustomerCommand(
        customerId: Long,
        value: String
    ) {
        this.customerId = customerId
        this.value = value
    }

    override fun execute(): List<ContactDto> {
        return when (customerId) {
            EMPTY_ID -> {
                when (value.isEmpty()) {
                    true -> customerService.getCustomers()
                    else -> customerService.getCustomersByNameOrSurname(value)
                }
            }

            else -> listOf(customerService.getCustomerById(customerId))
        }
    }
}