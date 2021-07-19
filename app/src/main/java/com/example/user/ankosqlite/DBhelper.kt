package com.example.user.ankosqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBhelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "pembeli.v", null, 1) {
    companion object {
        private var instances:DBhelper? = null
        @Synchronized
        fun getInstance (ctx: Context) : DBhelper{
            if (instances == null){
                instances = DBhelper(ctx.applicationContext)
            }
            return instances as DBhelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Model.TABLE_NAME,true,
            Model.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Model.NAMA to TEXT,
            Model.ALAMAT to TEXT,
            Model.NOHP to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Model.TABLE_NAME, true)
    }
}

// biar databasenya bisa dipake
val Context.database : DBhelper
get() = DBhelper.getInstance(applicationContext)