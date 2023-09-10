package com.picPaySimplificado.controllers

import com.picPaySimplificado.dtos.TransactionDTO
import com.picPaySimplificado.services.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping
    fun createTransaction(@RequestBody transactionDTO: TransactionDTO) = ResponseEntity.ok(transactionService.createTransaction(transactionDTO))
}