package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.widget.Button
import android.widget.EditText

class auditors : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auditors)
        val db=DBHelper(this,null)
        val auditor_db=db.writableDatabase
        val db_data=db.get_all()
        val name=findViewById<EditText>(R.id.auditor_name)
        val prof=findViewById<EditText>(R.id.auditor_prof)
        val lvl=findViewById<EditText>(R.id.auditor_lvl)
        val add_auditor_button=findViewById<Button>(R.id.add_auditor_button)
        lvl.text
        add_auditor_button.setOnClickListener{
            db.addRow(name.text.toString(),prof.text.toString(),lvl.text.toString())
        }
    }
}