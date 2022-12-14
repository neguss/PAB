package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children


class CheckActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        val data_list=findViewById<LinearLayout>(R.id.main_list)
        var headers= emptyArray<View>()
        var rows= emptyArray<View>()
        var tv= emptyArray<View>()
        var chk1= emptyArray<View>()
        var chk2= emptyArray<View>()
        for (v in data_list.children){//separate fly's from rows
            when(v.id){
                R.id.p1,R.id.p2,R.id.p3,R.id.p4,R.id.p5,R.id.p6,R.id.p7 -> headers+=v
                    else ->rows+=v
            }
        }
        for (i in rows.indices){
            var tmp=findViewById<ConstraintLayout>(rows[i].id)
            tv+=tmp.getChildAt(0)
            chk1+=tmp.getChildAt(1)
            chk2+=tmp.getChildAt(2)
        }
        var chk_results=ByteArray(chk1.size){0}

        for (i in chk1.indices){
            chk1[i].setOnClickListener{
                var tmp1=findViewById<CheckBox>(chk1[i].id)
                var tmp2=findViewById<CheckBox>(chk2[i].id)
                if (tmp1.isChecked) {
                    chk_results[i]=1
                    tmp2.isChecked = false
                }
            }
            chk2[i].setOnClickListener{
                var tmp1=findViewById<CheckBox>(chk1[i].id)
                var tmp2=findViewById<CheckBox>(chk2[i].id)
                if (tmp2.isChecked) {
                    chk_results[i]=2
                    tmp1.isChecked = false
                }
            }
        }

        var save_btn=findViewById<Button>(R.id.save_btn)
        save_btn.setOnClickListener{
            var intent=Intent()
            intent.putExtra("results",chk_results)
            setResult(2,intent)
            finish()
        }
        Toast.makeText(this,"213",Toast.LENGTH_SHORT)
    }
}