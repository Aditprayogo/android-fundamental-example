package com.aditprayogo.myquote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAllQuotes.setOnClickListener(this)

        getRandomQuotes()
    }

    private fun getRandomQuotes() {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://programming-quotes-api.herokuapp.com/quotes/random"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                //jika koneksi berhasil
                progressBar.visibility = View.INVISIBLE

                val result = responseBody?.let {
                    String(it)
                }

                Log.d(TAG,result)

                try {
                    val responeObject = JSONObject(result)

                    val quote = responeObject.getString("en")
                    val author = responeObject.getString("author")

                    tvAuthor.text = author
                    tvQuote.text = quote

                }catch (e: Exception) {

                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()

                }

            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                //jika koneksi gagal
                progressBar.visibility = View.INVISIBLE

                val errorMessage = when(statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode: Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()

            }

        })
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnAllQuotes -> {
                startActivity(Intent(this, ListQuotesActivity::class.java))
            }
        }
    }
}
