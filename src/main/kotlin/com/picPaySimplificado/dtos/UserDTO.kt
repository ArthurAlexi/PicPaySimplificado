package com.picPaySimplificado.dtos

import com.picPaySimplificado.domain.user.UserType
import java.math.BigDecimal

@JvmRecord
data class UserDTO(val firstName: String,
                   val lastName: String,
                   val document: String,
                   val email: String,
                   val password: String,
                   val balance: BigDecimal,
                   val type: UserType,)