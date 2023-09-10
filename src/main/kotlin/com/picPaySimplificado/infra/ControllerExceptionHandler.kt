package com.picPaySimplificado.infra

import com.picPaySimplificado.dtos.ExceptionDTO
import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun threatDuplicateEntry(exception: DataIntegrityViolationException) =
        ResponseEntity.badRequest().body(
            ExceptionDTO(message = "already registered user", statusCode = "400")
        )
    @ExceptionHandler(EntityNotFoundException::class)
    fun threat404(exception: EntityNotFoundException) =
        ResponseEntity.notFound().build<EntityNotFoundException>()

    @ExceptionHandler(Exception::class)
    fun threatGeneralException(exception: Exception) =
        ResponseEntity.internalServerError().body(
            ExceptionDTO(message = exception.message ?: "Internal Server Error", statusCode = "500")
        )
}