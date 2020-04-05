package com.aditprayogo.mytestingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvText: TextView
    private lateinit var btnSetValue: Button
    private lateinit var imgPriv: ImageView

    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_text)
        btnSetValue = findViewById(R.id.btn_set_value)
        imgPriv = findViewById(R.id.img_preview)

        Glide.with(this)
            .load(R.drawable.gambar)
            .into(imgPriv)

        names.add("Adit ganteng")
        names.add("Adit jelek")

        btnSetValue.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_set_value -> {

                val name = StringBuilder()

                for (i in 0..1){
                    name.append(names[i]).append("\n")
                }

                tvText.text = name.toString()
            }
        }
    }
}
