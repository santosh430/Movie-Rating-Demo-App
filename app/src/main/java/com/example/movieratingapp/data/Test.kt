package com.example.movieratingapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(
    val title:String,
    val originalname:String,
    val overview:String
):Parcelable
