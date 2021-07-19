package com.example.user.ankosqlite

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity

class RVAdapter (val context: Context, val items: ArrayList<Model>):RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(items[p1])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(model: Model) {
            itemView.itemNama.text = model.nama
            itemView.itemAlamat.text = model.alamat
            itemView.itemHp.text = model.nohp

            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainActivity>(
                    "nama" to model.nama,
                    "alamat" to model.alamat,
                    "nohp" to model.nohp
                )
                (itemView.context as Activity).finish()
            }

            itemView.btnHapus.setOnClickListener {
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setTitle("PERINGATAN !!!")
                dialog.setMessage("Apakah anda ingin menghapus data ini ?")
                dialog.setPositiveButton("YA") { dialog, which ->
                    itemView.context.database.use {
                        delete(Model.TABLE_NAME, "(${Model.NAMA} = {nama})", "nama" to model.nama.toString())
                        itemView.context.startActivity<LihatDataActivity>()
                        (itemView.context as Activity).finish()
                    }
                }

                dialog.setNegativeButton("TIDAK") { dialog, which ->

                }

                val a = dialog.create()
                a.show()
            }
        }
    }
}
