package com.mykieta.sasinmedmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Reader
import java.util.*
import kotlin.collections.ArrayList

class ShowQueueActivity : AppCompatActivity() {
    lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_queue)

        findViewsById()
        setFuelBasePath()
        getVisits()
    }

    private fun findViewsById() {
        info = findViewById(R.id.info)
    }

    private fun setFuelBasePath() {
        FuelManager.instance.basePath = "http://192.168.1.6:5000"
    }

    private fun getVisits() {
        val httpAsync = "/mobile/search".httpGet()
            .responseObject(Visit.Deserializer()) { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        info.text = getString(R.string.info_queue_error)
                    }
                    is Result.Success -> {
                        val data: Array<Visit>? = result.component1()
                        if (data != null) {
                            info.text = getString(R.string.info_queue_patient)
                            val recyclerView = findViewById<RecyclerView>(R.id.queue_of_visits)
                            val layoutManager = LinearLayoutManager(this)
                            recyclerView.layoutManager = layoutManager
                            val recyclerAdapter = RecyclerAdapter(data)
                            recyclerView.adapter = recyclerAdapter
                        } else {
                            info.text = getString(R.string.info_queue_empty)
                        }
                    }
                }
            }
        httpAsync.join()
    }

    data class Visit(
        var dateOfVisit: String, var doctor: String, var patient: String, var timeOfVisit: String
    ) {
        class Deserializer : ResponseDeserializable<Array<Visit>> {
            override fun deserialize(content: String): Array<Visit> =
                Gson().fromJson(content, Array<Visit>::class.java)
        }
    }
}