package com.example.user.ankosqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nama = intent.getStringExtra("nama")
        var alamat = intent.getStringExtra("alamat")
        var nohp = intent.getStringExtra("nohp")

        if (nama.isNullOrBlank()) {
            btnUpdate.isEnabled = false
        } else{
            btnTambah.isEnabled = false
            edtNama.setText(nama)
            edtAlamat.setText(alamat)
            edtHp.setText(nohp)
        }

        btnTambah.setOnClickListener {
            addData()
            clearData()
        }

        btnLihat.setOnClickListener {
            startActivity<LihatDataActivity>()
            finish()
        }

        btnUpdate.setOnClickListener {
            database.use {
                update(Model.TABLE_NAME,
                    Model.NAMA to edtNama.text.toString(),
                    Model.ALAMAT to edtAlamat.text.toString(),
                    Model.NOHP to edtHp.text.toString())
                    .whereArgs("${Model.NAMA} = {nama}", "nama" to nama).exec()

                toast("Data berhasil DI TAMBAHKAN")
                clearData()
            }
        }
    }

    private fun addData() {
        database.use {
            insert(Model.TABLE_NAME,
                Model.NAMA to edtNama.text.toString(),
                Model.ALAMAT to edtAlamat.text.toString(),
                Model.NOHP to edtHp.text.toString())

                toast("Data berhasil DI TAMBAHKAN")
        }
    }

    private fun clearData() {
        edtNama.text.clear()
        edtAlamat.text.clear()
        edtHp.text.clear()
    }

}
