package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.content.Intent
import androidx.core.content.ContextCompat
import java.io.File
import android.content.pm.PackageManager
import android.icu.text.SymbolTable
import android.os.Build
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
        val chk_activity=Intent(this,CheckActivity::class.java)
        var test1:String="Это тестовая строка, смирись"
        var test2:String=""
        var test3:String=""
        var output:String=""
        var nm:String="1_от_27.10.2022"
        var pers= arrayOf("Басов С.А.","Божко Д.В","Бойко Д.В","Большаков В.В.","Ворыпаев С.В","Егупов Н.А.","Зелепукин Д.А.","Илюшов И.Н.","Кайряк А.М.","Королев А.М.","Кузнецов А.С.","Куницин С.Н.","Левин А.А.","Логач Е.С.","Лямин П.И.","Миндагалиев Р.С.","Морозов Р.А.","Морхов Д.В.","Никитенко А.А.","Никитин А.А.","Потапов А.В.","Рудов М.В.","Скворцов И.А.","Смирнов Р.В.","Сухов В.А.","Халеев С.И.","Черпита О.В.","Чурилов Э.В.","Шацких С.А.")
        var auditorlvl= arrayOf(1,2,2,3,3,3,3,3,3,3,2,3,2,1,3,3,3,3,2,2,3,3,2,3,3,3,3,1,3)
        var auditor= arrayOf("Начальник филиала","Ведущий инженер-энергетик","Старший мастер по ремонту скважин (капитальному, подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Механик","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Начальник цеха","Мастер по ремонту (капитальному,подземному)","Начальник базы","Заместитель главного инженера по ОТ, ПБ,П и ООС","Инженер по КИПиА","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Старший мастер по ремонту скважин (капитальному, подземному)","Старший мастер по ремонту скважин (капитальному, подземному)","Старший механик","Старший механик","Ведущий инженер по ремонту","Мастер по ремонту","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Мастер по ремонту (капитальному,подземному)","Главный инженер","Мастер по ремонту (капитальному,подземному)")
        var persadapt=ArrayAdapter(this,com.google.android.material.R.layout.mtrl_auto_complete_simple_item,pers)
        fio.setAdapter(persadapt)
        var curr_dt=LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
        val shift:Int= (cos((test1[0].code+test1[1].code).toDouble()) *352).toInt()
        for (i in 0..test1.length-1){
            if (i%2==0){
                test2+=Char((test1[i].code-shift))
            }else{
                test2+=Char((test1[i].code-shift*2))
            }
        }

        for (i in 0..test2.length-1){
            if (i%2==0){
                test3+=Char((test2[i].code+shift))
            }else{
                test3+=Char((test2[i].code+shift*2))
            }
        }

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

                //out.appendText(""+npp.text +",")
                //out.appendText(""+fio.text+",")
                var chosenone=pers.indexOf(fio.text.toString())
                //out.appendText(""+auditor[chosenone]+",")
                //out.appendText(""+auditorlvl[chosenone]+",")
                //out.appendText(""+subdivision.text+",")
                //out.appendText(""+site.text+",")
                //out.appendText(""+job.text+",")
                //out.appendText(""+dt.text+",")
                //out.appendText(""+watchers.text+",")
                //out.appendText(""+tm.text+",")
                output=""+npp.text +","+fio.text+","+auditor[chosenone]+","+auditorlvl[chosenone]+","+subdivision.text+","+site.text+","+job.text+","+dt.text+","+watchers.text+","+tm.text+","

                for (i in chk_list.indices) {
                    if(i!=chk_list.size-1){
                        //out.appendText(chk_list[i].toString()+",")
                        output+=chk_list[i].toString()+","}
                    else{
                        output+=(chk_list[i].toString())
                        //output += chk_list[i].toString()
                    }
                }
                var testt=output.toByteArray()
                //
                out.appendText(output)
                Toast.makeText(this, "Отчет успешно сохранен по пути"+"/storage/emulated/0/Download/"+npp.text+"_от_"+curr_dt.replace('/','.'), Toast.LENGTH_SHORT)
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