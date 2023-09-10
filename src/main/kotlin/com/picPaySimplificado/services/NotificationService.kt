package com.picPaySimplificado.services


import com.picPaySimplificado.domain.user.User
import com.picPaySimplificado.dtos.NotificationDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NotificationService {

    @Autowired
    private val restTemplate =  RestTemplate()

    fun sendNotification(user: User, message: String){
        val email = user.email

        val notificationRequest = NotificationDTO(email, message)
        val notificationResponse: ResponseEntity<String.Companion> = this.restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",notificationRequest, String.javaClass)

        if(notificationResponse.statusCode != HttpStatus.OK){
            throw Exception("Notification service is down")
        }
    }
}