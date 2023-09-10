package com.picPaySimplificado.services

import com.picPaySimplificado.domain.transaction.Transaction
import com.picPaySimplificado.domain.user.User
import com.picPaySimplificado.dtos.TransactionDTO
import com.picPaySimplificado.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.picPaySimplificado.services.UserService
import com.picPaySimplificado.services.NotificationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactionService(
    @Autowired
    private val userService: UserService,
    @Autowired
    private val transactionRepository: TransactionRepository,
    @Autowired
    private val notificationService: NotificationService,
) {

    @Autowired
    private val restTemplate =  RestTemplate()

    fun createTransaction( transactionDTO :TransactionDTO): Transaction{
        val sender : User = userService.findUserById(transactionDTO.senderId)
        val receiver : User = userService.findUserById(transactionDTO.receiverId)

        this.userService.validateTransaction(sender, transactionDTO.value)

        if(!this.authorizedTransaction(sender, transactionDTO.value)){
            throw Exception("unauthorized Transaction")
        }

        val newTransaction : Transaction = Transaction(sender = sender, receiver = receiver, amount = transactionDTO.value, timestamp= LocalDateTime.now())

        sender.balance = sender.balance.subtract(transactionDTO.value)
        receiver.balance = receiver.balance.add(transactionDTO.value)

        this.transactionRepository.save(newTransaction)
        this.userService.saveUser(sender)
        this.userService.saveUser(receiver)

//        this.notificationService.sendNotification(sender, "transaction successful")
//        this.notificationService.sendNotification(receiver, "transaction successful")

        return newTransaction
    }


    fun authorizedTransaction(sender: User, value: BigDecimal) : Boolean  {
        val URL  = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6"
        val authorizationResponse:  ResponseEntity<Map<*, *>>  = this.restTemplate.getForEntity(URL, Map::class.java)

        if( authorizationResponse.statusCode == HttpStatus.OK  ){
            val message : String = (authorizationResponse.body?.get("message") ?: "Unauthorized").toString()
            return "autorizado".equals(message, ignoreCase = true)
        }else return false
    }
}