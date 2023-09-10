package com.picPaySimplificado.repositories

import com.picPaySimplificado.domain.transaction.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository: JpaRepository<Transaction, String> {

}