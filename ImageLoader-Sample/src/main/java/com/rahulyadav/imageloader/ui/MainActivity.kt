package com.rahulyadav.imageloader.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rahulyadav.imageloader.R
import com.rahulyadav.imageloader.model
import com.rahulyadav.imageloader.network.NetworkService
import com.rahulyadav.imageloader.utils.BASEURL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val rec = findViewById<RecyclerView>(R.id.imagev)

        adapter = Adapter(ArrayList())


        rec.layoutManager = LinearLayoutManager(this)
        rec.addItemDecoration(DividerItemDecoration(rec.context, (rec.layoutManager as LinearLayoutManager).orientation))
        rec.adapter = adapter
        callapI()

    }



    fun callapI(){
        NetworkService.provideNetWorkService().getTopHuntHeadLines(BASEURL).enqueue(object :Callback<List<model>>{
            override fun onResponse(call: Call<List<model>>, response: Response<List<model>>) {
                if (response.isSuccessful) {

                    Log.d("ashish", "onResponse: ${Gson().toJson(response.body())}")

                    if (response.body()!= null) {
                        response.body()?.let {
                            adapter.addData(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<model>>, t: Throwable) {
                Log.d("ashish", "onFailure: ${t.message}")
            }

        })

    }
}