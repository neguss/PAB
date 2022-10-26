package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.io.File
import android.content.pm.PackageManager
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : Activity() {

    lateinit var chk_list:ByteArray

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val npp=findViewById<EditText>(R.id.npp_value)
        val subdivision=findViewById<EditText>(R.id.subdivision_value)
        val site=findViewById<EditText>(R.id.site_value)
        val job=findViewById<EditText>(R.id.job_value)
        val dt=findViewById<TextView>(R.id.date_value)
        val watchers=findViewById<EditText>(R.id.watchers_value)
        val tm=findViewById<EditText>(R.id.time_value)
        val start_button=findViewById<Button>(R.id.start_btn)
        val save_button=findViewById<Button>(R.id.Save)
        val chk_activity=Intent(this,CheckActivity::class.java)

        var curr_dt=LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        dt.text=curr_dt
        start_button.setOnClickListener{
            startActivityForResult(chk_activity,1)
        }

        save_button.setOnClickListener{
            if(ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                val out = File("/storage/emulated/0/Download/"+"Протокол "+npp.text+"_от_"+curr_dt.replace('/','.')+".txt")
                if (!out.exists())
                    out.createNewFile()
                else {
                    out.delete()
                    out.createNewFile()
                }
                out.appendText(""+npp.text +",")
                out.appendText(""+subdivision.text+",")
                out.appendText(""+site.text+",")
                out.appendText(""+job.text+",")
                out.appendText(""+dt.text+",")
                out.appendText(""+watchers.text+",")
                out.appendText(""+tm.text+",")

                for (i in chk_list.indices) {
                    if(i!=chk_list.size-1)
                        out.appendText(chk_list[i].toString()+",")
                    else
                        out.appendText(chk_list[i].toString())
                }
                Toast.makeText(this, "Отчет успешно сохранен по пути"+"/storage/emulated/0/Download/"+"Протокол "+npp.text+"_от_"+curr_dt.replace('/','.')+".csv", Toast.LENGTH_SHORT)
            }
            else {
                Toast.makeText(this, "Нет доступа к внутреннему хранилищу", Toast.LENGTH_SHORT)
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),12 )
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data==null)
            return
        if (resultCode==2 && requestCode==1){
            chk_list= data.getByteArrayExtra("results")!!
            Toast.makeText(this,"done",Toast.LENGTH_SHORT)
        }
    }
}
/*

 */