package com.wcano.primeraapp
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wcano.primeraapp.Data.LoginRequest
import com.wcano.primeraapp.Data.LoginResponse
import com.wcano.primeraapp.UI.Home
import com.wcano.primeraapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    //implementacion del viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textUser!!.setText("etson@gmail.com")

        binding.btnGuardar?.setOnClickListener {

            val retrofit = Retrofit.Builder()
                .baseUrl("http://www.balam-knights.com/accesos_dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val user = binding.textUser!!.text.toString()
            val password = binding.textPassword!!.text.toString()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.login(LoginRequest(user, password))

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {

                        startActivity( Intent(this@MainActivity, Home::class.java))

                    } else {

                        val toast = Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    val toast = Toast.makeText(this@MainActivity, "Error $t", Toast.LENGTH_SHORT)
                    toast.show()
                }
            })


        }
    }


}

