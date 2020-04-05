package com.aditprayogo.mypreloaddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.mypreloaddata.adapter.MahasiswaAdapter
import com.aditprayogo.mypreloaddata.db.MahasiswaHelper
import kotlinx.android.synthetic.main.activity_mahasiswa.*

class MahasiswaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)

        recylerview.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()

        recylerview.adapter = mahasiswaAdapter
        val mahasiswaHelper = MahasiswaHelper(this)

        mahasiswaHelper.open()
        val mahasiswaModels = mahasiswaHelper.getAllData()

        mahasiswaHelper.close()
        mahasiswaAdapter.setData(mahasiswaModels)
    }
}
