package com.example.movieratingapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movieratingapp.data.Result
import kotlinx.android.synthetic.main.activity_demo.*

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        Log.e("ti","hello")
        supportActionBar?.hide()

        val dataInDataClass = intent.getParcelableExtra<Result>("data")


        //setting textviews

        tv_name.text = dataInDataClass?.title
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + dataInDataClass?.poster_path).into(iv_description)
        tv_overview.text = dataInDataClass?.overview
        tv_genre.text = ("Eng | Hollywood |" + dataInDataClass?.release_date?.subSequence(0, 4))
        rb.rating = dataInDataClass?.vote_average?.toFloat()!! / 2
        tv_rating.text = dataInDataClass.vote_average.toString()

    }
}