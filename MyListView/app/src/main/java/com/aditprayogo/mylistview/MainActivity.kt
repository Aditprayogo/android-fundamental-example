package com.aditprayogo.mylistview

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val data = arrayOf("Cut Nyak Dien", "Ki Hajar Dewantara", "Moh Yamin", "Patimura", "R A Kartini", "Sukarno")


    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray

    private var heroes = arrayListOf<Hero>()

    private lateinit var heroAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        heroAdapter = HeroAdapter(this)

        lv_list.adapter = heroAdapter

        prepare()
        addItem()

        //adding on click pada listview
        lv_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            Toast.makeText(this,heroes[position].name, Toast.LENGTH_SHORT).show()

        }

    }

    private fun prepare() {

        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)

    }

    private fun addItem() {

        for (position in dataName.indices){
            val hero = Hero(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDescription[position]
            )

            heroes.add(hero)
        }
        //add data ke heroes list
        heroAdapter.heroes = heroes
    }
}
