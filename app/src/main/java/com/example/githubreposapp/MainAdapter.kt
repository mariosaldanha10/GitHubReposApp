package com.example.githubreposapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("USER DETAILS", "REPOS 1", "REPOS 2", "REPOS 3")
    // numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.accounts.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // val videoTitles = videoTitles.get(position)
        val account = homeFeed.accounts.get(position)
        holder?.view?.textView_video_title?.text = account.login

    }
}
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}