package com.irvanw.challengechaptertwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import java.lang.ref.WeakReference
import java.text.DecimalFormat

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

        val edtMoney : EditText = findViewById(R.id.edtCOS)

        edtMoney.setMaskingMoney("Rp. ")

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
    interface MyTextWatcher: TextWatcher {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    fun String.monetize(): String = if (this.isEmpty()) "0"
    else DecimalFormat("#,###").format(this.replace("[^\\d]".toRegex(),"").toLong())

    fun EditText.setMaskingMoney(currencyText: String) {
        this.addTextChangedListener(object: MyTextWatcher{
            val editTextWeakReference: WeakReference<EditText> = WeakReference<EditText>(this@setMaskingMoney)
            override fun afterTextChanged(editable: Editable?) {
                val editText = editTextWeakReference.get() ?: return
                val s = editable.toString()
                editText.removeTextChangedListener(this)
                val cleanString = s.replace("[Rp,. ]".toRegex(), "")
                val newval = currencyText + cleanString.monetize()

                editText.setText(newval)
                editText.setSelection(newval.length)
                editText.addTextChangedListener(this)
            }
        })
    }


}