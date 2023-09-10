package com.picPaySimplificado.dtos
@JvmRecord
data class ExceptionDTO(
    val message: String,
    val statusCode: String,
)