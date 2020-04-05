package com.aditprayogo.myintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_move_with_object.*

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val person = intent.getParcelableExtra(EXTRA_PERSON) as Person
        val text = "Name: ${person.name.toString()}, \nEmail : ${person.email.toString()}, \nAge: ${person.age.toString()}, \nLocation: ${person.city.toString()}"

        tv_object_recived.text = text

    }
}
