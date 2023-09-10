package com.picPaySimplificado.services

import com.picPaySimplificado.domain.user.User
import com.picPaySimplificado.domain.user.UserType
import com.picPaySimplificado.dtos.UserDTO
import com.picPaySimplificado.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class UserService(@Autowired private val userRepository : UserRepository) {

    fun validateTransaction(sender: User, amount: BigDecimal) {
        if(sender.type != UserType.COMMOM){
            throw RuntimeException("Merchant type user is not authorized for this transaction")
        }

        if(sender.balance < amount){
            throw RuntimeException("user does not have enough balance")
        }

    }

    fun findUserById(id : String): User = userRepository.findUserById(id)
        .orElseThrow{RuntimeException("User does not exist")}

    fun saveUser(userDTO : UserDTO) = userRepository.save(User(userDTO))

    fun saveUser(user : User) = userRepository.save(user)

    fun getAllUsers(): List<User> = userRepository.findAll()
}