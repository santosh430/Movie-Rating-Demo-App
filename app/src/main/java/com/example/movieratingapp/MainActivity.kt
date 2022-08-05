package com.example.movieratingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieratingapp.data.DataAPIClass
import com.example.movieratingapp.data.Result
import com.example.movieratingapp.databinding.ActivityMainBinding
import com.example.movieratingapp.networkcalls.InterfaceAPI
import com.example.movieratingapp.networkcalls.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),InterfaceClickListener {


    var list = ArrayList<Int>()
    lateinit var myBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        myBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            getData()
            Log.e("add in main",list.size.toString())

        }
    }

    private fun getData() {

        val myApi: InterfaceAPI = RetrofitHelper.getInstance().create(InterfaceAPI::class.java)
        val example: Call<DataAPIClass> = myApi.getData()


        example.enqueue(object : Callback<DataAPIClass?> {
            override fun onResponse(
                call: Call<DataAPIClass?>,
                response: Response<DataAPIClass?>
            ) {

                val myData = response.body()
                list.add(myData!!.page)

                Log.e("add",list.size.toString())


                myBinding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    Log.e("List size of list", list.toString())

                    adapter =
                        Adapter(this@MainActivity, myData.results, this@MainActivity)


                }

            }

            override fun onFailure(call: Call<DataAPIClass?>, t: Throwable) {
                Log.e("TAG", "${t}")
            }
        })
    }

    override fun clickListener(obj: Result) {

        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("data", obj)
        startActivity(intent)

    }
}