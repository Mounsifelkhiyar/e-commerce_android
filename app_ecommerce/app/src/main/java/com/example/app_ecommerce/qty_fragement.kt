package com.example.app_ecommerce

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [qty_fragement.newInstance] factory method to
 * create an instance of this fragment.
 */
class qty_fragement : android.app.DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_qty_fragement, container, false)

        var et=v.findViewById<EditText>(R.id.et_qty)
            var bt=v.findViewById<Button>(R.id.btn_qty)
        bt.setOnClickListener {
            var url="http://192.168.0.134/e_commerce_android/add_temp.php?mobile="+UserInfo.mobile+"&itemid="+UserInfo.itemId+
                    "&qty="+et.text.toString()
            var rq:RequestQueue=Volley.newRequestQueue(activity)
            var st=StringRequest(Request.Method.GET,url,Response.Listener { response ->
                    var i=Intent(activity,Order_Act::class.java)
                startActivity(i)

            },Response.ErrorListener { error ->
                Toast.makeText(activity,error.message,Toast.LENGTH_SHORT).show()
            })
            rq.add(st)
        }
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment qty_fragement.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            qty_fragement().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}