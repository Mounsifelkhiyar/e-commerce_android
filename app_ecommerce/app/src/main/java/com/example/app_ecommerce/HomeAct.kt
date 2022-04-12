package com.example.app_ecommerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url="http://192.168.0.134/e_commerce_android/get_cat.php"
        var list=ArrayList<String>()
        var rq:RequestQueue=Volley.newRequestQueue(this)
        var rs=JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->

            for (x in 0..response.length()-1)
                list.add(response.getJSONObject(x).getString("category"))
                var adp= ArrayAdapter(this,R.layout.text_style,list)
                 homeidcategory.adapter=adp

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
        })
        rq.add(rs)

        homeidcategory.setOnItemClickListener { parent, view, i, id ->
            var cat:String=list[i]
            var obj= Intent(this,item_select::class.java)
            obj.putExtra("cat",cat)
            startActivity(obj)
        }

    }


}