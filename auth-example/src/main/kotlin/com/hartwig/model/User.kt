package com.hartwig.model

import io.micronaut.core.annotation.Introspected
import javax.persistence.*

@Entity
@Introspected
data class User(
    @Id
    val cpf: Long,
    @Column
    val password: String
)
