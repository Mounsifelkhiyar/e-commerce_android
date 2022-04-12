package com.example.app_ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_creer_compte.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_signup.setOnClickListener {
            var i= Intent(this,creer_compte::class.java)
            startActivity(i)
        }

        login_btn.setOnClickListener {
            var url = "http://192.168.0.134/e_commerce_android/login.php?mobile="+ login_mobile.text.toString() +"&password="+ login_password.text.toString()
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url, Response.Listener { response ->
                if(response.equals("0"))
                    Toast.makeText(this, "login est echouer", Toast.LENGTH_SHORT).show()
                else
                {
                    UserInfo.mobile=login_mobile.text.toString()
                    var i = Intent(this, HomeAct::class.java)
                    startActivity(i)
                }
            },Response.ErrorListener { error ->
                println(error.message)
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })
            println(sr.toString())
            rq.add(sr)
        }


    }
}