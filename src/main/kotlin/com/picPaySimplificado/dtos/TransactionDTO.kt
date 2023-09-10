package com.picPaySimplificado.dtos

import java.math.BigDecimal

@JvmRecord
data class TransactionDTO(val value : BigDecimal, val senderId: String, val receiverId: String)