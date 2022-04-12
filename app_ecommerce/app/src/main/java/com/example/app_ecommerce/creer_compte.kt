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

class creer_compte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_compte)

        reg_signup.setOnClickListener {

            if(reg_password.text.toString()==reg_confirm.text.toString())
            {
                var url = "http://192.168.0.134/e_commerce_android/add_user.php?mobile="+ reg_mobile.text.toString() +"&password="+ reg_password.text.toString() +"&name="+ reg_name.text.toString() +"&address="+ reg_adresse.text.toString()

                var rq:RequestQueue=Volley.newRequestQueue(this)
                var sr=StringRequest(Request.Method.GET,url,Response.Listener { response ->
                    if(response.equals("0"))
                            Toast.makeText(this, "compte  deja cree", Toast.LENGTH_SHORT).show()
                        else
                            //Toast.makeText(this, "compte est creer", Toast.LENGTH_SHORT).show()
                                UserInfo.mobile=reg_mobile.text.toString()
                                var i = Intent(this,HomeAct::class.java)
                                startActivity(i)
                    }

                    ,Response.ErrorListener { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                    })
                        rq.add(sr)
            }
            else
                Toast.makeText(this, "mot de pass n'est pas identique", Toast.LENGTH_SHORT).show()

        }

    }
}