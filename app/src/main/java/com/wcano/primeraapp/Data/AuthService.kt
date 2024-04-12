package com.wcano.primeraapp.Data

import java.util.UUID

class AuthService {


    private val userDatabase = mutableMapOf<String, String>()

    init {

        userDatabase["admin"] = "1234"
        userDatabase["user"] = "123456"

    }

        fun login(username: String, password: String): String? {
            val storedPassword = userDatabase[username]
            if (storedPassword == password) {

                return generateSessionToken()
            }
            return null
        }

        private fun generateSessionToken(): String {
            return UUID.randomUUID().toString()
        }
}