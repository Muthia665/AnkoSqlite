package com.example.user.ankosqlite

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.widget.Adapter

import kotlinx.android.synthetic.main.activity_lihat_data.*
import kotlinx.android.synthetic.main.content_lihat_data.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class LihatDataActivity : AppCompatActivity() {

    private lateinit var adapter: RVAdapter
    private var  listpembeli = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data)
        setSupportActionBar(toolbar)

        adapter = RVAdapter(this, listpembeli)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        getData()

        fab.setOnClickListener { view ->
            startActivity<MainActivity>()
            finish()
        }
    }

    private fun getData() {
        database.use {
            listpembeli.clear()
            var result = select(Model.TABLE_NAME)
            var dataPembeli = result.parseList(classParser<Model>())
            listpembeli.addAll(dataPembeli)
            adapter.notifyDataSetChanged()
        }
    }

}
