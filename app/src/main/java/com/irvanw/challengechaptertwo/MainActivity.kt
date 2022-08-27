package com.irvanw.challengechaptertwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        val cvBtnSatu = findViewById<CardView>(R.id.rbSatu)
        val cvBtnDua = findViewById<CardView>(R.id.rbDua)
        val cvBtnTiga = findViewById<CardView>(R.id.rbTiga)

        val btnInsideSatu = findViewById<Button>(R.id.rbInsideSatu)
        val btnInsideDua = findViewById<Button>(R.id.rbInsideDua)
        val btnInsideTiga = findViewById<Button>(R.id.rbInsideTiga)

        val radioGrp = findViewById<RadioGroup>(R.id.rGroup)

        var moneyReal : String

        val selectedAmazing : ImageView = findViewById(R.id.ivAmazing)
        val selectedGood : ImageView = findViewById(R.id.ivGood)
        val selectedOk : ImageView = findViewById(R.id.ivOk)

        val edtMoney : EditText = findViewById(R.id.edtCOS)

        edtMoney.setMaskingMoney("Rp. ")


        val strEdtMoney = edtMoney.setMaskingMoney("Rp. ")


        btnSubmit.setOnClickListener(){
            if (edtMoney.length() == 0) {
                Toast.makeText(this, "0 Money", Toast.LENGTH_SHORT).show()
            } else {
                if(selectedAmazing.isVisible){
                    removeKoma(edtMoney.text.toString())
                    MyCustomDialog().show(supportFragmentManager, "MyCustomFragment")
                } else if(selectedGood.isVisible){
                    Toast.makeText(this, "Selected good", Toast.LENGTH_SHORT).show()
                } else if(selectedOk.isVisible){
                    Toast.makeText(this, "Selected Ok", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Kamu tidak akan memberikan tips ? ", Toast.LENGTH_SHORT).show()
                }
            }
        }

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

    fun EditText.setMaskingMoney(currencyText: String){
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

    fun hitungTIp(cos : Int,presentase:Double):Int{
        var hasilHitung : Int

        hasilHitung = (cos / presentase).roundToInt()

        return hasilHitung
    }

    fun removeKoma(oldString : String): String{
        var rp : String = oldString
        var lenghthRp = rp.length
        var withoutRp = rp.substring(4,lenghthRp)
        var withtoutComa:String = withoutRp.replace(",","")
        Toast.makeText(this, "${withtoutComa}", Toast.LENGTH_SHORT).show()
        return  withtoutComa
    }

}

class MyCustomDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.dialogue_thanks, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}