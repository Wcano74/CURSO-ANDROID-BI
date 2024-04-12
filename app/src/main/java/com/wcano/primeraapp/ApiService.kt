package com.wcano.primeraapp

import com.wcano.primeraapp.Data.LoginRequest
import com.wcano.primeraapp.Data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("x-api-key: 81818BBF-C772-411D-9BFA-3CAFA12D6077")
    @POST("Auth/")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}