package com.irvanw.challengechaptertwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cvBtnSatu = findViewById<CardView>(R.id.rbSatu)
        val cvBtnDua = findViewById<CardView>(R.id.rbDua)
        val cvBtnTiga = findViewById<CardView>(R.id.rbTiga)

        val btnInsideSatu = findViewById<Button>(R.id.rbInsideSatu)
        val btnInsideDua = findViewById<Button>(R.id.rbInsideDua)
        val btnInsideTiga = findViewById<Button>(R.id.rbInsideTiga)

        val radioGrp = findViewById<RadioGroup>(R.id.rGroup)


        val selectedAmazing : ImageView = findViewById(R.id.ivAmazing)
        val selectedGood : ImageView = findViewById(R.id.ivGood)
        val selectedOk : ImageView = findViewById(R.id.ivOk)



        cvBtnSatu.setOnClickListener{
            selectedGood.isVisible = false
            selectedOk.isVisible = false
            if(selectedAmazing.isVisible){
                selectedAmazing.isVisible = false
            } else {
                selectedAmazing.isVisible = true
            }
        }

        cvBtnDua.setOnClickListener{
            selectedAmazing.isVisible = false
            selectedOk.isVisible = false
            if(selectedGood.isVisible){
                selectedGood.isVisible = false
            } else {
                selectedGood.isVisible = true
            }
        }

        cvBtnTiga.setOnClickListener{
            selectedGood.isVisible = false
            selectedAmazing.isVisible = false
            if(selectedOk.isVisible){
                selectedOk.isVisible = false
            } else {
                selectedOk.isVisible = true
            }
        }


    }
}