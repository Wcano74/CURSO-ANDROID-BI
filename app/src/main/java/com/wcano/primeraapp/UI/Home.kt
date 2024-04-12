package com.wcano.primeraapp.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.wcano.primeraapp.ApiService
import com.wcano.primeraapp.Data.AuthService
import com.wcano.primeraapp.Data.LoginRequest
import com.wcano.primeraapp.Data.LoginResponse
import com.wcano.primeraapp.R
import com.wcano.primeraapp.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home : AppCompatActivity() {

    //implementacion del viewBinding
     private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Apiusuario()
    }

    fun Apiusuario(){

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.balam-knights.com/accesos_dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.login(LoginRequest("etson@gmail.com", "123456"))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {

                    binding.textViewUsername.setText(response.body()!!.name)
                    binding.textViewRole.setText(response.body()!!.userID)
                    binding.textViewAddres.setText(response.body()!!.address)
                    binding.textViewSessionToken.setText(response.body()!!.token)

                    Glide.with(binding.root)
                        .load(response.body()!!.imageurl.toString())
                        .into(binding.imageViewProfile)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val toast = Toast.makeText(this@Home, "Error $t", Toast.LENGTH_SHORT)
                toast.show()
            }
        })

    }

}