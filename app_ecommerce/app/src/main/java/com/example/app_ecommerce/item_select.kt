package com.example.app_ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_item_select.*

class item_select : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_select)

        var cat: String? = intent.getStringExtra("cat")
        println(cat)
        var url="http://192.168.0.134/e_commerce_android/get_items.php?category="+cat
        var list=ArrayList<Item>()
        var rs:RequestQueue=Volley.newRequestQueue(this)
        println("****************")
        var js= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->

            for (x in 0..response.length()-1)
            //println(Item(response.getJSONObject(x).getInt("id"),response.getJSONObject(x).getString("name"),response.getJSONObject(x).getDouble("price"),response.getJSONObject(x).getString("photo").toString()))
                list.add(Item(response.getJSONObject(x).getInt("id"),response.getJSONObject(x).getString("name"),response.getJSONObject(x).getDouble("price"),response.getJSONObject(x).getString("photo")))

            var adp= ItemAdapter(this,list)
            item_rv.layoutManager=LinearLayoutManager(this)
            item_rv.adapter=adp

        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
        })
        rs.add(js)
    }
}