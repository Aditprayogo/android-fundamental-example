package com.aditprayogo.myworkmanager



import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.Data.Builder
import androidx.work.WorkInfo.State
import com.aditprayogo.myworkmanager.R.layout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var periodicWorkRequest: PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOneTimeTask.setOnClickListener(this)
        btnPeriodicTask.setOnClickListener(this)
        btnCancelTask.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnOneTimeTask -> startOneTimeTask()
            R.id.btnPeriodicTask -> startPeriodicTask()
            R.id.btnCancelTask -> cancelPriodicTask()
        }
    }

    private fun startOneTimeTask() {

        textStatus.text = getString(R.string.status)

        val data = Builder()
            .putString(MyWorker.EXTRA_CITY, editCity.text.toString())
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(oneTimeWorkRequest)
        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this@MainActivity, object : Observer<WorkInfo> {

            override fun onChanged(workInfo: WorkInfo) {
                val status = workInfo.state.name
                textStatus.append("\n" + status)
            }
        })
    }

    private fun startPeriodicTask() {
        textStatus.text = getString(R.string.status)
        val data = Builder()
            .putString(MyWorker.EXTRA_CITY, editCity.text.toString())
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        periodicWorkRequest = PeriodicWorkRequest
            .Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueue(periodicWorkRequest)
        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this@MainActivity, object : Observer<WorkInfo> {

            override fun onChanged(workInfo: WorkInfo) {
                val status = workInfo.state.name
                textStatus.append("\n" + status)
                btnCancelTask.isEnabled = false
                if (workInfo.state == State.ENQUEUED) {
                    btnCancelTask.isEnabled = true
                }
            }
        })
    }

    private fun cancelPriodicTask() {
        WorkManager.getInstance()
            .cancelWorkById(periodicWorkRequest.id)
    }
}
