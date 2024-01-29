@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.userservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(nullable = false, length = 50, unique = true)
    var email: String? = "", // var 로 하지 않으면 ModelMapper에서 값이 안들어옴
    @Column(nullable = false, length = 50)
    var name: String? = "",
    @Column(nullable = false, unique = true)
    var userId: String? = "",
    @Column(nullable = false, unique = true)
    var encryptedPwd: String? = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
)
