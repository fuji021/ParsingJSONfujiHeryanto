package id.ac.pelitabangsa.parsingjsonfujiheryanto

import android.os.Bundle
import android.os.Handler
import android.telecom.Call
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.pelitabangsa.parsingjsonfujiheryanto.model.DataItem
import id.ac.pelitabangsa.parsingjsonfujiheryanto.model.ResponseUser
import id.ac.pelitabangsa.parsingjsonfujiheryanto.network.ApiConfig
import kotlinx.android.synthetic.main.activity_main.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter =  UserAdapter(mutableListOf())


        rv_users.setHasFixedSize(true)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = adapter
        getUser()
    }

private fun getUser(){
    val client = ApiConfig.getApiService().getListUsers("1")

    client.engueue(object : Callback<ResponseUser> {
        override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>){
         if (response.isSuccessful){
             val dataArray = response.body()?.Data as List<DataItem>
             for (data in dataArray){
                 adapter.addUser(data)
             }
         }
        }

        override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            t.printStackTrace()
        }
    })
   }
}