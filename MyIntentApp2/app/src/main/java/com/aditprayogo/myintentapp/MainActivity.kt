package com.aditprayogo.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_CODE = 100

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_move_activity.setOnClickListener {

            val intent = Intent(this, MoveActivity::class.java)
            startActivity(intent)

        }

        btn_move_with_data.setOnClickListener {

            val intent = Intent(this, MoveWithDataActivity::class.java)
            intent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Aditiya Prayogo")
            intent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
            startActivity(intent)
        }

        btn_move_with_object.setOnClickListener {
            val person = Person(
                "Aditiya Prayogo",
                19,
                "adit.ihzar@gmail.com",
                "Tangerang"
            )

            val intent = Intent(this,MoveWithObjectActivity::class.java)
            intent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
            startActivity(intent)

        }

        btn_dial_number.setOnClickListener {

            val dialNumber = "081282432271"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$dialNumber"))
            startActivity(intent)

        }

        btn_move_for_result.setOnClickListener {

            val moveForResult = Intent(this,MoveForResultActivity::class.java)
            startActivityForResult(moveForResult, REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE){

            if (resultCode == MoveForResultActivity.RESULT_CODE){

                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tv_result.text = "Hasil : $selectedValue"

            }
        }
    }
}
