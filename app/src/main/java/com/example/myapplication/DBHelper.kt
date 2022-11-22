package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    val DATABASE_CREATE="Create table if not exists "+ TABLE_NAME +"("+ ID_COL+" integer primary key autoincrement,"+ NAME_COL+" text,"+ DESCRIPTION+" text,"+LEVEL+" integer)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }

    override fun onDowngrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }



    fun addRow(name:String, desc : String, lvl:String){
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(DESCRIPTION, desc)
        values.put(LEVEL, lvl)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun get_all(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME, null)

    }
    fun getPers(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM PERSON", null)

    }



    companion object{
        private const val DATABASE_NAME = "audit_db"
        private const val DATABASE_VERSION= 1
        val TABLE_NAME = "Auditor_table"
        val ID_COL = "ID"
        val NAME_COL   = "AUDITOR_NAME"
        val DESCRIPTION = "TITLE"
        val LEVEL="LVL"

    }
}
