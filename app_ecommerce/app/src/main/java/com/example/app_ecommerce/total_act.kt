package com.example.app_ecommerce

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_total.*
import java.math.BigDecimal

class total_act : AppCompatActivity() {
    var config:PayPalConfiguration?=null
    var amount:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        btsite.setOnClickListener {
            var site=Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/"))
            startActivity(site)
        }
        btlocal.setOnClickListener {
            var local=Intent(Intent.ACTION_VIEW, Uri.parse("geo:37,32323,-765,1231233"))
            startActivity(local)
        }

        var url="http://192.168.0.134/e_commerce_android/get_total.php?bill_no="+intent.getStringExtra("bno")
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var st= StringRequest(Request.Method.GET,url, Response.Listener { response ->
            id_total.text=response
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })

        rq.add(st)
        config=PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(UserInfo.client_id)
        var i=Intent(this,PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        startService(i)
        println("************")

        idbt_paypal.setOnClickListener {

            amount=id_total.text.toString().toDouble()
            var payment=PayPalPayment(BigDecimal.valueOf(amount),"USD","Sales App",PayPalPayment.PAYMENT_INTENT_SALE)
            var intent=Intent(this,PaymentActivity::class.java)
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment)
            startActivityForResult(intent,123)
            println("$$$$$$$$$$$$")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                var obj=Intent(this,Confirm_Act::class.java)
                startActivity(obj)
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this,PayPalService::class.java))
        super.onDestroy()
    }


}