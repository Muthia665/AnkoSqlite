package com.example.user.ankosqlite

class Model (var id:Long?, var nama:String?, var alamat:String?, var nohp:String?){
    companion object {
        val TABLE_NAME:String = "TABLE_NAME"
        val ID:String = "ID"
        val NAMA:String = "NAMA"
        val ALAMAT:String = "ALAMAT"
        val NOHP:String = "NOHP"
    }
}