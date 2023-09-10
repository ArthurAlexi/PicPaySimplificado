package com.picPaySimplificado.domain.user

import com.picPaySimplificado.dtos.UserDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.math.BigDecimal
import java.util.UUID

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
data class User(
    @Id
    private val id: String = UUID.randomUUID().toString(),

    val firstName: String,

    val lastName: String,

    @Column(unique = true)
    val document :String,

    @Column(unique = true)
    val email : String,

    val password : String,

    var balance : BigDecimal,

    @Enumerated(EnumType.STRING)
    var type: UserType
){
    constructor(userDTO: UserDTO): this(
        UUID.randomUUID().toString(),
        userDTO.firstName,
        userDTO.lastName,
        userDTO.document,
        userDTO.email,
        userDTO.password,
        userDTO.balance,
        userDTO.type)
}
