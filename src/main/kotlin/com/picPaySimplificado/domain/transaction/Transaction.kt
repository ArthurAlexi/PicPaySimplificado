package com.picPaySimplificado.domain.transaction

import com.picPaySimplificado.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
data class Transaction(
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: String = UUID.randomUUID().toString(),

    val amount: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "sender_id")
    val sender: User,

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    val receiver: User,

    val timestamp: LocalDateTime
){
    constructor(sender: User, receiver: User, amount: BigDecimal, timestamp: LocalDateTime)
            : this(UUID.randomUUID().toString(), amount,sender, receiver,timestamp)
}