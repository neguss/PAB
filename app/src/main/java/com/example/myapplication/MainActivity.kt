package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.app.Notification
import android.os.Bundle
import android.content.Intent
import androidx.core.content.ContextCompat
import java.io.File
import android.content.pm.PackageManager
import android.icu.text.DateFormat
import android.icu.text.SymbolTable
import android.os.Build
import android.os.Environment
import android.os.Message
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import java.text.Format
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.math.cos

class MainActivity : Activity() {

    lateinit var chk_list:ByteArray

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val npp=findViewById<EditText>(R.id.npp_value)
        val fio=findViewById<AutoCompleteTextView>(R.id.pers_value)
        val subdivision=findViewById<EditText>(R.id.subdivision_value)
        val site=findViewById<EditText>(R.id.site_value)
        val job=findViewById<EditText>(R.id.job_value)
        val dt=findViewById<TextView>(R.id.date_value)
        val watchers=findViewById<EditText>(R.id.watchers_value)
        val tm=findViewById<EditText>(R.id.time_value)
        val start_button=findViewById<Button>(R.id.start_btn)
        val save_button=findViewById<Button>(R.id.Save)
        val add_button=findViewById<ImageView>(R.id.AddPersButton)
        val dt_pick=findViewById<ConstraintLayout>(R.id.date_layout)
        val dtpick=findViewById<DatePicker>(R.id.DatePicker)
        val dt_button=findViewById<Button>(R.id.date_button)
        val chk_activity=Intent(this,CheckActivity::class.java)
        val add_activity=Intent(this,auditors::class.java)
        var output:String=""
        var pers= arrayOf("Басов С.А.","Божко Д.В","Бойко Д.В","Большаков В.В.","Ворыпаев С.В","Егупов Н.А.","Зелепукин Д.А.","Илюшов И.Н.","Кайряк А.М.","Королев А.М.","Кузнецов А.С.","Куницин С.Н.","Левин А.А.","Логач Е.С.","Лямин П.И.","Миндагалиев Р.С.","Морозов Р.А.","Морхов Д.В.","Никитенко А.А.","Никитин А.А.","Потапов А.В.","Рудов М.В.","Скворцов И.А.","Смирнов Р.В.","Сухов В.А.","Халеев С.И.","Черпита О.В.","Чурилов Э.В.","Шацких С.А.")
        var auditorlvl= arrayOf(1,2,2,3,3,3,3,3,3,3,2,3,2,1,3,3,3,3,2,2,3,3,2,3,3,3,3,1,3)
        var auditor= arrayOf("Начальник филиала","Ведущий инженер-энергетик","Старший мастер по ремонту скважин (капитальному, подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Механик","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Начальник цеха","Мастер по ремонту (капитальному,подземному)","Начальник базы","Заместитель главного инженера по ОТ, ПБ,П и ООС","Инженер по КИПиА","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Старший мастер по ремонту скважин (капитальному, подземному)","Старший мастер по ремонту скважин (капитальному, подземному)","Старший механик","Старший механик","Ведущий инженер по ремонту","Мастер по ремонту","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Главный инженер","Мастер по ремонту (капитальному,подземному)")
        var persadapt=ArrayAdapter(this,com.google.android.material.R.layout.mtrl_auto_complete_simple_item,pers)
        fio.setAdapter(persadapt)
        var curr_dt=LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),12 )
        val db=DBHelper(this,null)
        val auditor_db=db.writableDatabase
        var db_source:String=Environment.DIRECTORY_DOCUMENTS

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

                var chosenone=pers.indexOf(fio.text.toString())
                if (chosenone>0)
                    output=""+npp.text +";"+fio.text+";"+auditor[chosenone]+";"+auditorlvl[chosenone]+";"+subdivision.text+";"+site.text+";"+job.text+";"+dt.text+";"+watchers.text+";"+tm.text+";"
                else
                    output=""+npp.text +";"+fio.text+";"+"Должность"+";"+"Уровень"+";"+subdivision.text+";"+site.text+";"+job.text+";"+dt.text+";"+watchers.text+";"+tm.text+";"

                for (i in chk_list.indices) {
                    if(i!=chk_list.size-1){
                        output+=chk_list[i].toString()+";"}
                    else{
                        output+=(chk_list[i].toString())
                    }
                }
                var testt=output.toByteArray()
                for (i in 0..testt.size-1){
                    testt[i]=(testt[i]+1).toByte()
                }
                out.appendText(output)
                Toast.makeText(this, "Отчет успешно сохранен по пути"+"/storage/emulated/0/Download/"+npp.text+"_от_"+curr_dt.replace('/','.'), Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Нет доступа к внутреннему хранилищу", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),12 )
            }

        }
        add_button.setOnClickListener{
            startActivityForResult(add_activity,1)
        }

        dt.setOnClickListener {
            dt_pick.visibility=View.VISIBLE
        }

        dt_button.setOnClickListener{
            val d=dtpick.dayOfMonth
            val m=dtpick.month+1
            val y=dtpick.year
            dt.text=d.toString()+"/"+m.toString()+"/"+y.toString()
            dt_pick.visibility=View.INVISIBLE
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data==null)
            return
        if (resultCode==2 && requestCode==1){
            chk_list= data.getByteArrayExtra("results")!!
            Toast.makeText(this,"done",Toast.LENGTH_SHORT).show()
        }
    }
}