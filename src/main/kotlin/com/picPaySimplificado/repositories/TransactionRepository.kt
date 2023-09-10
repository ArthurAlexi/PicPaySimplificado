package com.picPaySimplificado.repositories

import com.picPaySimplificado.domain.transaction.Transaction
import org.springframework.data.jpa.repository.JpaRepository

class TransactionRepository: JpaRepository<Transaction, String> {

}