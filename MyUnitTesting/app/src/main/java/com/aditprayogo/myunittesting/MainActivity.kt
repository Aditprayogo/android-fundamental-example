package com.aditprayogo.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText

    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button
    private lateinit var btnSave: Button

    private lateinit var mainViewModel: MainViewModel

    private lateinit var tvResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view model
        mainViewModel = MainViewModel(CuboidModel())

        edtHeight = findViewById(R.id.edt_height)
        edtWidth = findViewById(R.id.edt_width)
        edtLength = findViewById(R.id.edt_length)

        tvResult = findViewById(R.id.tv_result)

        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)


    }

    override fun onClick(v: View) {
       val length = edtLength.text.toString().trim()
       val width = edtWidth.text.toString().trim()
       val height = edtHeight.text.toString().trim()

        when{
            //dicek
            length.isEmpty() -> edtLength.error = "Field ini tidak boleh kosong"
            width.isEmpty() -> edtWidth.error = "Field ini tidak boleh kosong"
            height.isEmpty() -> edtHeight.error = "Field ini tidak boleh kosong"

            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when(v.id){
                    R.id.btn_save -> {
                        mainViewModel.save(l,w,h)
                        visible()
                    }

                    R.id.btn_calculate_volume -> {
                        tvResult.text = mainViewModel.getvolume().toString()
                        gone()
                    }

                    R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }

                    R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnSave.visibility = View.GONE
    }

    private fun gone() {
        btnCalculateVolume.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnSave.visibility = View.VISIBLE
    }
}
