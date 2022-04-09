package com.example.githubreposapp

import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.video_row.*
import kotlinx.android.synthetic.main.video_row.view.*
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  recyclerView_main.setBackgroundColor(Color.BLACK)

    recyclerView_main.layoutManager = LinearLayoutManager(this)
      //  recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }
    fun fetchJson(){
        println("Attempting to Fetch JSON")

        val url = "https://api.github.com/users/mariosaldanha10"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val account = gson.fromJson(body, Account::class.java)

                runOnUiThread {
                    findViewById<TextView>(R.id.user_name).text = account.login
                    findViewById<TextView>(R.id.id_user).text = account.id
                   // findViewById<TextView>(R.id.user_imageView).text = account.avatar_url
                    findViewById<TextView>(R.id.user_gravatar).text = account.gravatar_id
                    findViewById<TextView>(R.id.user_url).text = account.url
                    findViewById<TextView>(R.id.user_html_url).text = account.html_url
                    findViewById<TextView>(R.id.user_followers).text = account.followers_url
                    findViewById<TextView>(R.id.user_following).text = account.following_url

                }


            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}
class HomeFeed (val accounts: List<Account>)

class Account (val login: String,
               val id: String,
           //    val avatar_url: String,
               val gravatar_id: String,
               val url: String,
               val html_url: String,
               val followers_url: String,
               val following_url: String) {
}


