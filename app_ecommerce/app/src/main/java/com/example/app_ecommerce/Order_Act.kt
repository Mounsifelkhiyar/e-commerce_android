package com.example.app_ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.homeidcategory
import kotlinx.android.synthetic.main.activity_order.*

class Order_Act : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var url="http://192.168.0.134/e_commerce_android/get_temp.php?mobile="+UserInfo.mobile
        var list=ArrayList<String>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var rs= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->

            for (x in 0..response.length()-1)
                list.add("Name :"+response.getJSONObject(x).getString("name")+"\n"+
                        "Price :"+response.getJSONObject(x).getString("price")+"\n"+
                        "Quantite :"+response.getJSONObject(x).getString("qty"))
            var adp= ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
            list_order.adapter=adp

        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rq.add(rs)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item?.itemId==R.id.id_back_to_menu)
        {
            var i=Intent(this,HomeAct::class.java)
            startActivity(i)
        }
        if(item?.itemId==R.id.id_cancel)
        {
            var url="http://192.168.0.134/e_commerce_android/cancel_order.php?mobile="+UserInfo.mobile
            var rq:RequestQueue=Volley.newRequestQueue(this)
            var st= StringRequest(Request.Method.GET,url,Response.Listener { response ->
                var i=Intent(this,HomeAct::class.java)
                startActivity(i)
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
            })
            rq.add(st)
        }
        if(item?.itemId==R.id.id_confirm)
        {
            var url="http://192.168.0.134/e_commerce_android/confirm_order.php?mobile="+UserInfo.mobile
            var rq:RequestQueue=Volley.newRequestQueue(this)
            var st= StringRequest(Request.Method.GET,url,Response.Listener { response ->
                var i=Intent(this,total_act::class.java)
                i.putExtra("bno",response)
                startActivity(i)
            },Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
            })
            rq.add(st)
        }
        return super.onOptionsItemSelected(item)
    }
}