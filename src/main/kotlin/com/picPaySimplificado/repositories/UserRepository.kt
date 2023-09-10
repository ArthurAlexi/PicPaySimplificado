package com.picPaySimplificado.repositories

import com.picPaySimplificado.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

interface UserRepository: JpaRepository<User,String>  {
    fun findUserByDocument(document: String) : Optional<User>

    fun findUserById(id: String) : Optional<User>
}