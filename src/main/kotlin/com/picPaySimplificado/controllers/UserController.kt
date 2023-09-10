package com.picPaySimplificado.controllers

import com.picPaySimplificado.dtos.UserDTO
import com.picPaySimplificado.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody user: UserDTO) = ResponseEntity.ok(userService.saveUser(user))

    @GetMapping
    fun getAllUsers() = ResponseEntity.ok(userService.getAllUsers())
}