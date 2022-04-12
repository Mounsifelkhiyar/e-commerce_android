package com.example.app_ecommerce

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(var context:Context,var list:ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v: View = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(
            list[position].name,
            list[position].price,
            list[position].photo,
            list[position].id
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(n: String, p: Double, u: String, item_id: Int) {
            itemView.item_name.text = n
            itemView.item_prix.text = p.toString()
            var web: String = "http://192.168.0.134/e_commerce_android/image_items/" + u
            web = web.replace(" ", "%20")
            Picasso.with(itemView.context).load(web).into(itemView.item_photo)

            itemView.items_add.setOnClickListener {
                UserInfo.itemId = item_id

                var obj = qty_fragement()
                var manager= (itemView.context as Activity).fragmentManager
                //println(androidx.fragment.app.FragmentActivity.getSupportFragmentManager().)
                obj.show(manager,"qty")

            }
        }
    }
}