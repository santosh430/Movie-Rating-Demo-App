package com.example.movieratingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieratingapp.data.Result
import com.example.movieratingapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.recycler_adapter.view.*


class Adapter(private val context: MainActivity, private val results:List<Result>?, private val listener:InterfaceClickListener)
    : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var myBinding:ActivityMainBinding

    inner class ViewHolder(private var view: View):RecyclerView.ViewHolder(view){

        fun bind(data: Result) {

            //setting movie Names
            movieName(data)

            //setting realease year
            releaseYear(data)

            //setting images
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+data.poster_path)
                .into(view.iv_movieIcon)

            //setting stars
            view.rb_stars.rating= data.vote_average?.toFloat()!! /2

            //setting ratings
            view.tv_rating.text= data.vote_average.toString()+"/10"
        }

        private fun movieName(data: Result) {

            if (data.title != null) {
                view.tv_movieName.text = data.title
            } else if (data.original_title != null) {
                view.tv_movieName.text = data.original_title
            } else {
                view.tv_movieName.text = data.name
            }
        }

        private fun releaseYear(data: Result)
        {
            if(data.release_date!=null)
            {
                view.tv_year.text= data.release_date.subSequence(0,4).toString()
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data: Result = results!![position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
           listener.clickListener(data)
        }

    }

    override fun getItemCount(): Int {
        return results!!.size
    }
}
